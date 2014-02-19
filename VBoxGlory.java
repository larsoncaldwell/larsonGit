// implements runnable
// void run()
//for the runnables: while (VBoxGlory.getInstance().isThreadRunning())
//  try {thread.sleep(100); dosomething();}
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

   public void begin()
   {
       System.out.println("I am started");
       //mRunnableThread.add(mRunnable);
       //while(mRunnable.isRunning())
       //{
         //keep runnables running
       //}
   }

   public void stop()
   {
       System.out.println("I am stopped");
       //mRunnableThread.remove();
   }

    public void enterRunnable()
    {
        String runnableName = mEnter.getText();
        mEnter.setText("");
        System.out.println(runnableName);
    }

   public void start(final Stage pPrimaryStage)
    {

       mEventPush =
	   new EventHandler<ActionEvent>()
       {
	   public void handle(ActionEvent event)
	   {
	       begin();
	   }
       };

       mEventPop =
	   new EventHandler<ActionEvent>()
       {
	   public void handle(ActionEvent event)
	   {
	       stop();
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
		  //enterRunnable();
              }
	  });

       mPrimaryStage = pPrimaryStage;
       mPrimaryStage.setScene(mScene);
       mPrimaryStage.setTitle("This is the Glory");
       mPrimaryStage.show();
   }
}