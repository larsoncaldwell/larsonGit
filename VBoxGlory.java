import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VBoxGlory
   extends Application
{
    TextField enter = new TextField();
    Label labelEnter = new Label("Enter Runnables:");
   //Runnable mRunable;
   //RunnableCollection mRunnableCollection;
   VBox mVBox;

    ActionHandeler mStartPush;

    ActionHandler mStopPush;
   
   
   public static void main(String[] args)
   {
       launch(args);
   }

   public void begin()
   {
       //mRunnableThread.add(mRunnable);
       //while(mRunnable.isRunning())
       //{
         //keep runnables running
       //}
   }

   public void stop()
   {
       //mRunnableThread.remove();
   }

   public void start(final Stage primaryStage)
   {
       BorderPane theBorder = new BorderPane();
       RunnableNode runnableIN  = new RunnableNode("The Input", "Start");
       RunnableNode runnableOUT = new RunnableNode("The Output", "Stop"); 

       HBox topInfo = new HBox();

       enter.setPrefSize(250, 25);
       topInfo.getChildren().add(labelEnter);
       topInfo.getChildren().add(enter);
       topInfo.setAlignment(Pos.CENTER);
       topInfo.setSpacing(10);
       topInfo.setHgrow(topInfo, Priority.ALWAYS);

       theBorder.setLeft(runnableIN);
       theBorder.setRight(runnableOUT);
       theBorder.setTop(topInfo);

       Scene theScene = new Scene(theBorder, 550, 500);


       mStartPush =
          new ActionHandler<actionEvent>()
          {
	     public void handle(ActionEvent event)
	     {
	        begin();
	     }
          };

       mStopPush =
          new ActionHandler<actionEvent>()
          {
             public void handle(ActionEvent event)
             {
	        stop();
             }
          };



       primaryStage.setScene(theScene);
       primaryStage.setTitle("This is a test");
       primaryStage.show();
   }

   public class StartActionListenter
      implements ActionListener
   {
       public void actionPerformed(ActionEvent event)
       {
	   //add app to the runnable
       }
   }

   public class StopActionListenter
      implements ActionListener
   {
       public void actionPerformed(ActionEvent event)
       {
           //remove app from the runnable
       }
   }
}