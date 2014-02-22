import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderPane;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This is a runnable application that RunnableHost can run.  It is a simple
 * rock, poper scissors game that you can cheat on.  I thought that it would be
 * a fun way to implement the features of Javafx and Runnable that I have
 * learned.  Enjoy!
 *
 * @author Larson Caldwell
 */

public class ComputerFight
   extends Application
   implements Runnable
{
   //////////////////// private variables

   /**
    * are you a cheater
    */
   private boolean mCheater;

   /**
    * the computer's hidden move
    */
   private String mTruth;

   /**
    * number of wins
    */
   private int mWin;

   /**
    * number of losses
    */
   private int mLose;

   /**
    * number of ties
    */
   private int mTie;

   /**
    * the commputer's move
    */
   private int mMove;


   /**
    * the border that will display the game
    */
   private BorderPane mBorderPane;


   /**
    * a label for the game
    */
   private Label mLabel;

   /**
    * ROCK button
    */
   private Button mRock;

   /**
    * PAPER button
    */
   private Button mPaper;

   /**
    * SCISSORS button
    */
   private Button mCut;

   /**
    * CHEAT button
    */
   private Button mCheat;

   /**
    * a list displaying the actions
    */
   private ListView<String> mTries;

   /**
    * label to display the cheat
    */
   private Label mCode;

   
   /**
    * a group to stor the VBoxes
    */
   private Group mRoot;

   /**
    * the box for the user buttons
    */
   private VBox mButtons;

   /**
    * the box for the action list
    */
   private VBox mList;

   /**
    * handler for the ROCK button
    */
   private EventHandler<ActionEvent> mSmash;

   /**
    * handler for the PAPER button
    */
   private EventHandler<ActionEvent> mFold;

   /**
    * handler for the SCISSORS button
    */
   private EventHandler<ActionEvent> mSnip;

   /**
    * handler for the CHEAT button
    */
   private EventHandler<ActionEvent> mPeak;

   /**
    * the window to display all the information
    */
   private Scene mScene;

   
   //////////////////// Methods

   /**
    * Main method that launches the applicaton.
    *
    * @param the first string
    */
   public static void main(String[] args)
   {
       launch(args);
   }

   /**
    * Uses the host to run the application.
    */
   public void run()
   {
      Process p = null;
      try
      {
         // application starts
         p = new ProcessBuilder("java", "ComputerFight").start();
      }
      catch (Exception e)
      {
      }

      // while the runnable is in the host's list
      while (RunnableHost.getInstance().isRunning())
      {
         try
         {
            Thread.sleep(1000);
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
      throws Exception
   {
      primaryStage.setTitle("Lets play a game");

      primaryStage.setOnCloseRequest(
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

      play(primaryStage);

      primaryStage.show();
   }

   /**
    * Sets the importaint information to connect buttons and set display.
    *
    * @param the stage to be used in the program
    */
   private void play(Stage primaryStage)
   {
      mCheater = false;
      mRoot = new Group();
      mScene = new Scene(mRoot, 500, 800);
      primaryStage.setScene(mScene);

      mSmash =
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               mCode.setText("");
               fight("R");
            }
         };

      mFold =
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               mCode.setText("");
               fight("P");
            }
         };

      mSnip =
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               mCode.setText("");
               fight("S");
            }
         };

      mPeak =
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               check();
               mCode.setText(mTruth);
            }
         };

      mButtons = new VBox(10);
      mList = new VBox(10);

      mLabel = new Label("Can you beat me?");
      mCode = new Label("");

      mTries = new ListView<String>();
      mTries.setPrefWidth(300);

      mRock  = new Button("  ROCK  ");
      mPaper = new Button("  PAPER ");
      mCut   = new Button("SCISSORS");
      mCheat = new Button("cheat");

      mRock.setOnAction(mSmash);
      mPaper.setOnAction(mFold);
      mCut.setOnAction(mSnip);
      mCheat.setOnAction(mPeak);

      mButtons.getChildren().add(mRock);
      mButtons.getChildren().add(mPaper);
      mButtons.getChildren().add(mCut);
      mButtons.getChildren().add(mCheat);
      mButtons.getChildren().add(mCode);

      mList.getChildren().add(mLabel);
      mList.getChildren().add(mTries);

      mButtons.setMargin(mRock, new Insets(50, 10, 10, 10));
      mButtons.setMargin(mPaper, new Insets(10));
      mButtons.setMargin(mCut, new Insets(10));
      mButtons.setMargin(mCheat, new Insets(500, 10, 10, 10));
      mButtons.setMargin(mCode, new Insets(10));

      mList.setMargin(mLabel, new Insets(50, 10, 10, 10));
      mList.setMargin(mTries, new Insets(10));

      mButtons.setVgrow(mRock, Priority.ALWAYS);
      mButtons.setVgrow(mPaper, Priority.ALWAYS);
      mButtons.setVgrow(mCut, Priority.ALWAYS);
      mButtons.setVgrow(mCheat, Priority.ALWAYS);
      mButtons.setVgrow(mCode, Priority.ALWAYS);
      mList.setVgrow(mTries, Priority.ALWAYS);
      mList.setVgrow(mLabel, Priority.ALWAYS);

      mBorderPane = new BorderPane();
      mBorderPane.prefHeightProperty().bind(mScene.heightProperty());
      mBorderPane.prefWidthProperty().bind(mScene.widthProperty());
      mBorderPane.setLeft(mButtons);
      mBorderPane.setRight(mList);

      mRoot.getChildren().add(mBorderPane);
   }

   /**
    * This method checks to see who wins.
    *
    * @param the players move
    */
   private void fight(String pAttack)
   {
      try
      {
         Thread.sleep(200);
      }
      catch (Exception e)
      {
      }

      int defence;

      if (mCheater)
      {
         defence = mMove;
      }
      else
      {
         defence = (int)Math.ceil(Math.random()*3);
      }

      String result = "";
 
      // 1: Rock
      // 2: Paper
      // 3: Scissor

      if (pAttack == "R")
      {
         if (defence == 1)
         {
             result += "ROCK! ... Tie";
             mWin  = 0;
             mLose = 0;
             mTie++;
         }
         else if (defence == 2)
         {
             result += "PAPER! ... I Win, Horray!";
             mWin  = 0;
             mLose++;
             mTie  = 0;
         }
         else if (defence == 3)
         {
             result += "SCISSORS! ... What! You Win?";
             mWin++;
             mLose = 0;
             mTie  = 0;
         }
      }
      else if (pAttack == "P")
      {
         if (defence == 1)
         {
            result += "ROCK! ... What! You Win?";
            mWin++;
            mLose = 0;
            mTie  = 0;
         }
         else if (defence == 2)
         {
            result += "PAPER! ... Tie";
            mWin  = 0;
            mLose = 0;
            mTie++;
         }
         else if (defence == 3)
         {
            result += "SCISSORS! ... I Win, Horray!";
            mWin  = 0;
            mLose++;
            mTie  = 0;
         }
      }
      else if (pAttack == "S")
      {
         if (defence == 1)
         {
            result += "ROCK ... I Win, Horray!";
            mWin  = 0;
            mLose++;
            mTie  = 0;
         }
         else if (defence == 2)
         {
            result += "PAPER! ... What! You Win?";
            mWin++;
            mLose = 0;
            mTie  = 0;
         }
       	 else if (defence == 3)
         {
            result += "SCISSORS! ... Tie";
            mWin  = 0;
            mLose = 0;
            mTie++;
         }
      }

      mTries.getItems().add(result);

      reactions();

      mCheater = false;
   }

   /**
    * The program will make comments about the game if so many wins or losses
    * happen.
    */
   private void reactions()
   {
      String reaction = "";

      if (mWin == 12)
      {
	  mTries.getItems().add(reaction);
          try
          {
	      Thread.sleep(2500);
	  }
          catch (Exception e)
          {
	  }
          Platform.exit();
          System.exit(0);
      }
      else if (mWin == 11)
      {
         reaction = "Cheat one more time, and I'm out.";
      }
      else if (mWin == 10)
      {
         reaction = "You ARE a cheater!";
      }
      else if (mWin == 9)
      {
         reaction = "Wait, are you cheating?";
      }  
      else if (mWin == 7)
      {
         reaction = "You are REALY good!";
      }  
      else if (mWin == 5)
      {
         reaction = "Wow you're good!";
      }
      else if (mWin == 3)
      {
         reaction = "Three in a row, not bad.";
      }

      else if (mLose == 10)
      {
         reaction = "Whatever, I dont mind!";
      }  
      else if (mLose == 9)
      {
         reaction = "Are you trying to lose?";
      }  
      else if (mLose == 7)
      {
         reaction = "Wow!  Realy.";
      }  
      else if (mLose == 5)
      {
         reaction = "I'm on a roll!";
      }
      else if (mLose == 3)
      {
         reaction = "Yes!  Three in a row!";
      } 

      else if (mTie == 10)
      {
         reaction = "I cant take it anymore!";
         mTries.getItems().add(reaction);
         try
         {
            Thread.sleep(2500);
         }
         catch (Exception e)
         {
         }
         Platform.exit();
         System.exit(0);
      }
      else if (mTie == 9)
      {
         reaction = "Aw, stop it!.";
      }
      else if (mTie == 8)
      {
         mTries.getItems().add("Alright heres what I'll do.");
         reaction = "I'll use ROCK.  Dont use ROCK OK?";
         mMove = 1;
         mCheater = true;
      }
      else if (mTie == 7)
      {
         reaction = "Alright, stop this.";
      }
      else if (mTie == 5)
      {
         reaction = "Stop copying me!";
      }
      else if (mTie == 3)
      {
         reaction = "What's the big idea.";
      }
      
      if (reaction != "")
      {
         mTries.getItems().add(reaction);
      }
   }

   /**
    * This method will give the player the ability to cheat.
    */
   private void check()
   {
       if (mCheater == false)
       {
          mMove = (int)Math.ceil(Math.random()*3);
       }

       mCheater = true;

       if (mMove == 1)
       {
          mTruth = "He will play rock.";
       }
       else if (mMove == 2)
       {
          mTruth = "He will play paper.";
       }
       else if (mMove == 3)
       {
          mTruth = "He will play scissors.";
       }
   }
}
