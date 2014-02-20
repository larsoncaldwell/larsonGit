import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
//import javafx.scene.control.Dialogs;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.SelectionMode;

public class VBoxGlory
   extends Application
{
    private TextField mEnter;
   private Label mLabel;
   private VBox mVBox;
   private Scene mScene;

   private EventHandler<ActionEvent> mEventPush;
   private EventHandler<ActionEvent> mEventPop;

   private Stage mPrimaryStage;

    //private Group mGroup;
   
   private BorderPane mBorder;
    private RunnableNode mRunnableIN;
    private RunnableNode mRunnableOUT;

   private HBox mTopBox = new HBox();

   
   public static void main(String[] args)
   {
       launch(args);
   }

    public void enterRunnable()
    {
        String runnableName = mEnter.getText();
        mEnter.setText("");
        if (load(runnableName) && startThread())
        {
	    mRunnableOUT.requestButtonFocus();
	}
    }

   public void start(final Stage pPrimaryStage)
    {

       mEventPush =
	   new EventHandler<ActionEvent>()
       {
	   public void handle(ActionEvent event)
	   {
	       startThread();
	   }
       };

       mEventPop =
	   new EventHandler<ActionEvent>()
       {
	   public void handle(ActionEvent event)
	   {
	       stopThread();
	   }
       };

	mRunnableIN  = new RunnableNode("The Input", "Start",
					SelectionMode.SINGLE, mEventPush);
	mRunnableOUT = new RunnableNode("The Output", "Stop",
					SelectionMode.MULTIPLE, mEventPop);

	//mGroup = new Group();
       mBorder = new BorderPane();

       mLabel = new Label("Enter Runnables:");
       mEnter = new TextField("");
       mEnter.setPrefSize(250, 25);
       mTopBox.getChildren().add(mLabel);
       mTopBox.getChildren().add(mEnter);
       mTopBox.setMargin(mLabel, new Insets(5, 5, 10, 75));
       mTopBox.setMargin(mEnter, new Insets(5, 75, 10, 5)); 
       mTopBox.setHgrow(mTopBox, Priority.ALWAYS);

       mBorder.setLeft(mRunnableIN);
       mBorder.setRight(mRunnableOUT);
       mBorder.setTop(mTopBox);

       mScene = new Scene(mBorder, 550, 500);

       mBorder.prefHeightProperty().bind(mScene.heightProperty());
       mBorder.prefWidthProperty().bind(mScene.widthProperty());

       mEnter.setOnAction(
          new EventHandler<ActionEvent>()
	  {
	      public void handle(ActionEvent event)
              {
		  enterRunnable();
              }
	  });

       mPrimaryStage = pPrimaryStage;
       mPrimaryStage.setScene(mScene);
       mPrimaryStage.setTitle("This is the Glory");
       mPrimaryStage.show();
   }


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

    private boolean startThread()
    {
	String name = mRunnableIN.getSelectedItem();
        Thread t = createThread(name);
        if (t != null)
        {
	    mRunnableOUT.push(t.getName());
            t.start();
            return true;
        }
        return false;
    }

    private void stopThread()
    {
	mRunnableOUT.popAll();
    }

    private Thread createThread(String pName)
    {
	Thread t = null;
        try
        {
	    Runnable r = (Runnable) Class.forName(pName).newInstance();
            t = new Thread(r);
            t.setName(r.getClass().getName() + t.getName());
        }
        catch (Exception e)
	{
	}
        //if (t == null)
	    //{
	    //System.out.println("FATAL ERROR");
	    //}
        return t;
    }

    public boolean isRunning(Runnable pRunnable)
    {
	return mRunnableOUT.inList(pRunnable.getClass().getName());
    }





























    private void showMessage(String pFirstText, String pSecondText)
    {
        //Dialogs.showInformationDialog(mPrimaryStage, pFirstText, pSecondText, "Warning");
        System.out.println(pFirstText + " " + " " + pSecondText + " ... Warning");
    }
}
