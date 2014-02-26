import java.util.*;
import javafx.util.Duration;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderPane;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.shape.Rectangle;

/**
 * This is a thread subclass aplication.  It gives you a ghostly friend.
 *
 * @author Larson Caldwell
 */

public class Ghost
   extends Application
   implements Runnable
{
   //////////////////// private variables

   /**
    * the border that will display the message
    */
   private BorderPane mBorderPane;

   /**
    * a label for the message
    */
   private Label mMessage;
   
   /**
    * a group to store the objects
    */
   private Group mRoot;

   /**
    * the window to display all the information
    */
   private Scene mScene;

   private HBox mBox;

   private File mTester;

   private Stage mPrimaryStage;

   private Boolean mInvisible = false;
   
   //////////////////// Methods

   public static void main(String[] args)
   {
       launch(args);
   }

   /**
    * Uses the host to run the application.
    */
   public void run()
   {
      File mTester = new File("just a test");
      Process p = null;
      try
      {
         // application starts
         p = new ProcessBuilder("java", "Ghost").start();
      }
      catch (Exception e)
      {
      }

      // while the runnable is in the host's list
      while (RunnableHost.getInstance().isRunning())// && (mTester.exists()))
      {
         try
         {
            Thread.sleep(100);
         }
         catch (Exception e)
         {
            System.out.println("ERROR");
         }
      }
      if (p != null)
      {
         p.destroy();
      }
   }

   /**
    * Start of the application.  Sets basic values and uses play method.
    *
    * @param the stage to be used in the program
    */
   public void start(Stage primaryStage)
   //throws Exception
   {
      mPrimaryStage = primaryStage;

      mPrimaryStage.setOnCloseRequest(
         new EventHandler<WindowEvent>()
         {
            public void handle(WindowEvent event) 
            {
               event.consume();
               try
               {
                  mTester.delete();
                  Platform.exit();
                  System.exit(0);
               }
               catch (Exception e)
       	       {
                  System.out.println(e.getMessage());
               }
            }
         });

      mRoot = new Group();
      mScene = new Scene(mRoot, 50, 20, Color.WHITE);
      mPrimaryStage.setScene(mScene);

      mMessage = new Label("BOOO!!!");

      mBorderPane = new BorderPane();

      mRoot.getChildren().add(mMessage);
      mRoot.getChildren().add(mBorderPane);

      int i = 0;

      mPrimaryStage.initStyle(StageStyle.UNDECORATED);

      Platform.runLater(new Runnable()
      {
         public void run()
         {
	    Timeline timeline = new Timeline();

	    timeline.setCycleCount(10000);

            timeline.getKeyFrames().add(
      	    new KeyFrame(Duration.millis(100),
            new EventHandler()
      	    {
	       public void handle(Event event)
	       {
		   mPrimaryStage.setY(mPrimaryStage.getY() + chose() * 3);
		   mPrimaryStage.setX(mPrimaryStage.getX() + chose() * 3);
                   
                   if (mInvisible)
                   {
		      mPrimaryStage.setOpacity(mPrimaryStage.getOpacity() + .05);
                   }
		   else
                   {
                      mPrimaryStage.setOpacity(mPrimaryStage.getOpacity() - .05);
                   }

                   if (mPrimaryStage.getOpacity() < 0.1)
	           {
                      mInvisible = true;
		   }
                   else if (mPrimaryStage.getOpacity() > .9)
		   {
                      mInvisible = false;
	           }
	       }
	    }));
         timeline.play();
         }
      });

      mPrimaryStage.show();
   }

   /**
    * Creates a random number of either 1 or -1.
    *
    * @return either 1 or -1
    */
   public int chose()
   {
      int answer = (int) Math.ceil(Math.random()* 2);
      if (answer == 1)
      {
         return -1;
      }
      return 1;
   }
}