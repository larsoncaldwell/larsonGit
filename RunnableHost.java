import java.util.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Dialogs;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class has the purpose of accepting and running Runnable files.  It is
 * the host to other files.
 *
 * @author Larson Caldwell
 */

public class RunnableHost
   extends Application
{
   //////////////////// private variables

   /**
    * The single instance of this singleton class.
    */
   private static RunnableHost cInstance = null;

   /**
    * The text field.
    */
   private TextField mEnter;
   
   /**
    * The label for the text field.
    */
   private Label mLabel;

   /**
    * For the RunnableNodes.
    */
   private VBox mVBox;

   /**
    * The scene of the display.
    */
   private Scene mScene;

   /**
    * The event handler for the START button.
    */
   private EventHandler<ActionEvent> mEventPush;

   /**
    * The event handler for the STOP button.
    */
   private EventHandler<ActionEvent> mEventPop;

   /**
    * The stage for the display.
    */
   private Stage mPrimaryStage;

   /**
    * The border to contain the visible objacts.
    */
   private BorderPane mBorder;

   /**
    * The VBox for the avalible runnables.
    */
   private RunnableNode mRunnableIN;

   /**
    * The VBox for the running runnables.
    */
   private RunnableNode mRunnableOUT;

   /**
    * A box to contain the label and textbox on the top.
    */
   private HBox mTopBox = new HBox();

   //////////////////// Constructor

   /**
    * This is the Constructor of the Runnable Host.  It is needed in order to
    * make Runnable Host a singleton.
    */
   public RunnableHost()
   {
      // the superclass constructor, Application, is called
      super();

      // the Runnable Host instance is created once, and only once
      synchronized (RunnableHost.class)
      {
         if (cInstance != null)
         {
            throw new UnsupportedOperationException(
            getClass() +
               " is a singleton. The constructor should be called only once.");
         }
         cInstance = this;
      }
   }

   //////////////////// Methods
   
   /**
    * The main method simply launches the application.
    *
    * @param the main string.
    */
   public static void main(String[] args)
   {
      launch(args);
   }

   /**
    * The start method is the true begining of the application.  It declares
    * the key member variables and sets their actions.  It also sets the window
    * up and displays it.
    *
    * @param the stage being used.
    */
   public void start(final Stage pPrimaryStage)
   {
      //
      // This anonymous inner class sets the event to follow the action of
      // pushing the button in Runnable in, the START button.
      //
      mEventPush =
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               // the selected thread is initiated
               startThread();
            }
         };

      //
      // This anonymous inner class sets the event to follow the action of
      // pushing the button in Runnable out, the STOP button.
      //
      mEventPop =
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               // the selected thread is closed
               stopThread();
            }
         };

      // two VBoxes will be used for both the avalible and running applications
      mRunnableIN  = new RunnableNode("Runnables", "Start",
				      SelectionMode.SINGLE, mEventPush, null);
      
      mRunnableOUT = new RunnableNode("Running Threads", "Stop",
				      SelectionMode.MULTIPLE, mEventPop, null);

      mBorder = new BorderPane();

      // top section initialization
      mLabel = new Label("Enter Runnable:");
      mEnter = new TextField("");
      mEnter.setPrefSize(250, 25);

      // top section HBox is set up
      mTopBox.getChildren().add(mLabel);
      mTopBox.getChildren().add(mEnter);
      mTopBox.setMargin(mLabel, new Insets(5, 5, 10, 75));
      mTopBox.setMargin(mEnter, new Insets(5, 75, 10, 5)); 
      mTopBox.setHgrow(mTopBox, Priority.ALWAYS);

      // the displaying window is given the V and H boxes
      mBorder.setLeft(mRunnableIN);
      mBorder.setRight(mRunnableOUT);
      mBorder.setTop(mTopBox);

      // window is given a size and property
      mScene = new Scene(mBorder, 550, 500);
      mBorder.prefHeightProperty().bind(mScene.heightProperty());
      mBorder.prefWidthProperty().bind(mScene.widthProperty());

      //
      // this sets the action of hitting the enter key when text is inside the
      // textfield.  If the text represents an application, the application
      // will be added into Runnable Out.
      //
      mEnter.setOnAction(
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               enterRunnable();
            }
         });

      // the window is set up
      mPrimaryStage = pPrimaryStage;
      mPrimaryStage.setScene(mScene);
      mPrimaryStage.setTitle("Welcome to the Host of Runners");

      // this closes the window if the X button is pushed
      mPrimaryStage.setOnCloseRequest(
         new EventHandler<WindowEvent>()
         {
            public void handle(WindowEvent event)
            {
               event.consume();
               try
               {
                  Platform.exit();
                  System.exit(0);
               }
               catch(Exception e)
               {
                  System.out.println(e.getMessage());
               }
            }
         });

      // window is displayed
      mPrimaryStage.show();
   }
   
   /**
    * After text has been entered into the textfield and the ENTER key has been
    * pushed, the runnable is loaded and put onto the list of posible runnables
    * in Runnable in.  If the text does not represent a runnable, it is not
    * added.  The textfield is reset.
    */
   public void enterRunnable()
   {
      String runnableName = mEnter.getText();
      mEnter.setText("");
      if (load(runnableName))
      {
         mRunnableIN.requestButtonFocus();
      }
   }

   /**
    * This loads a runnable onto the Runnable in list.  An error is displayed
    * if the text is not runnable.
    *
    * @param the name of the runnable
    *
    * @return whether or not the text is a runnable file
    */
   private boolean load(String pName)
   {
      Class runnable = null;
      boolean isRunnable = false;
      try
      {
         if ((pName != null) && (pName.length() > 0))
         {
       	    runnable = Class.forName(pName);
            isRunnable = Runnable.class.isAssignableFrom(runnable);
            if (isRunnable)
            {
               mRunnableIN.push(runnable.getName());
            }
            else
            {
      	       throw new ClassCastException(pName + " is not a Runnable.");
      	    }
         }
      }
      catch (Throwable t)
      {
         showMessage("Error loading Runnable", t.toString());
      }
      return (runnable != null && isRunnable);
   }

   /**
    * This method takes a runnable from the Runnable in list and puts it on the
    * Runnable out list.  The runnable starts.
    *
    * @return whether or not the thread was null
    */
   private boolean startThread()
   {
      // thread name retreaved
      String name = mRunnableIN.getSelectedItem();

      // runnable thread created
      Thread t = createThread(name);
      if (t != null)
      {
         mRunnableOUT.push(t.getName());
         t.start();
         return true;
      }
      return false;
   }

   /**
    * To stop a thread, simply remove it from the Runnables out list.
    */
   public void stopThread()
   {
      mRunnableOUT.popAll();
   }

   /**
    * A thread is created based on the imported name.
    *
    * @param the name of the runnable being started
    *
    * @return the newly created thread
    */
   private Thread createThread(String pName)
   {
      Thread t = null;
      try
      {
         // instance of the runnable name cast as a runnable
         Runnable r = (Runnable) Class.forName(pName).newInstance();
         t = new Thread(r);
         t.setName(r.getClass().getName() + t.getName());
      }
      catch (Exception e)
      {
         //should not be thrown
      }

      // t should not be null
      return t;
   }

   /**
    * Checks to see if the thread is still on the runnables out list.
    *
    * @return whether or not its still on the list
    */
   public boolean isRunning()
   {
      return (isRunning(Thread.currentThread()));
   }

   /**
    * gets the runnable thread and calls isRunning to see is it is still
    * running.
    *
    * @return whether or not it is still running
    */
   public boolean isRunning(Thread pRunnable)
   {
      return mRunnableOUT.inList(pRunnable.getName());
   }

   /**
    * Gets the singleton instance of RunnableHost.
    *
    * @return the one instance of Runnablehost
    */
   public static RunnableHost getInstance()
   {
      return cInstance;
   }

   public ListView getRunnableList()
   {
      return mRunnableOUT.getList();
   }

   /**
    * This message displays when text is entered as a runnable but it is not
    * recognised as one.
    *
    * @param an error message
    * @param the unrecognised text
    */
   private void showMessage(String pFirstText, String pSecondText)
   {
      Dialogs.showInformationDialog(mPrimaryStage, pFirstText,
                                    pSecondText, "Warning");
   }
}
