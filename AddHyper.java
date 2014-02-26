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
import javafx.stage.WindowEvent;

import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.shape.Rectangle;

/**
 * This is a thread subclass aplication.  It gives you an advertisement.
 *
 * @author Larson Caldwell
 */

public class AddHyper
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
         p = new ProcessBuilder("java", "AddHyper").start();
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

      mPrimaryStage.setTitle("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

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
               catch (Exception e)
       	       {
                  System.out.println(e.getMessage());
               }
            }
         });

      mRoot = new Group();
      mScene = new Scene(mRoot, 350, 50, Color.RED);
      mPrimaryStage.setScene(mScene);

      mMessage = new Label("Your Our 1,000,000 Viewer!!!!!!!!!!");
      mMessage.setFont(Font.font("Ariel", 20));

      Rectangle rect = new Rectangle(1000, 1000);

      mBox = new HBox(10);
      mBox.getChildren().add(mMessage);
      mBox.setMargin(mMessage, new Insets(10));

      mBorderPane = new BorderPane();

      mBorderPane.setCenter(mBox);

      mRoot.getChildren().add(rect);
      mRoot.getChildren().add(mBorderPane);
 
      FillTransition fill = new FillTransition(Duration.millis(50), rect, Color.RED,
                                               Color.YELLOW);
      fill.setCycleCount((20000));

      fill.setAutoReverse(true);

      fill.play();

      int i = 0;

      Platform.runLater(new Runnable()
      {
         public void run()
         {
	    Timeline timeline = new Timeline();

	    timeline.setCycleCount(100000000);

            timeline.getKeyFrames().add(
      	    new KeyFrame(Duration.millis(10),
            new EventHandler()
      	    {
	       public void handle(Event event)
	       {
                   int randY = ((int) Math.ceil(Math.random() * 10) 
				* chose());
		   int randX = ((int) Math.ceil(Math.random() * 10)
				* chose());
		   int randH = ((int) Math.ceil(Math.random() * 2)
                                * chose());
		   int randW = ((int) Math.ceil(Math.random() * 2)
                                * chose());

		   mPrimaryStage.setY(mPrimaryStage.getY() + randY);
		   mPrimaryStage.setX(mPrimaryStage.getX() + randX);

                   mPrimaryStage.setHeight(mPrimaryStage.getHeight() + randH);
		   mPrimaryStage.setWidth(mPrimaryStage.getWidth() + randW);  
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