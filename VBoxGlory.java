import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javafx.scene.layout.VBox;
//import javafx.geometry.Pos;

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
   //VBox mVBox;
   
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
       RunnableNode mRunnableIN  = new RunnableNode("The Input", "Start");
       RunnableNode mRunnableOUT = new RunnableNode("The Output", "Stop"); 

       theBorder.setLeft(mRunnableIN);
       theBorder.setRight(mRunnableOUT);
       theBorder.setTop(labelEnter);
       theBorder.setTop(enter);

       Scene theScene = new Scene(theBorder, 1000, 500);

       primaryStage.setScene(theScene);
       primaryStage.setTitle("This is a test");
       primaryStage.show();




       /**
      RunnableNode mRunnableIN  = new RunnableNode("The Input", "Start");
      RunnableNode mRunnableOUT = new RunnableNode("The Output", "Stop");
      EnterNode mEnter = new EnterNode("Enter Here:");

      mVBox.getChildren().addAll(mRunnableIN, mRunnableOUT);

      mVBox.setMargin(mRunnableIN, new Insets(10, 10, 10 ,10));
      mVBox.setAlignment(Pos.CENTER_LEFT);
      mVBox.setMargin(mRunnableOUT, new Insets(20, 20, 20 ,20));
      mVBox.setAlignment(Pos.CENTER_RIGHT);
      mVBox.setMargin(mEnter, new Insets(5, 5, 5 ,5));
      mVBox.setAlignment(Pos.TOP_CENTER);

      //mVBox.setVgrow(mRunnableOUT, Priority.ALWAYS);
      */
   }

   public class StartActionListenter
      implements ActionListener
   {
       public void actionPerformed(ActionEvent event)
       {
	   //start the runnable
       }
   }

   public class StopActionListenter
      implements ActionListener
   {
       public void actionPerformed(ActionEvent event)
       {
           //stop the runnable
       }
   }
}