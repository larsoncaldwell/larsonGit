import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SelectionMode;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ComputerFight
  extends Application
  implements Runnable
{
    private boolean mCheater;
    private String mTruth;
    private int mWin;
    private int mLose;
    private int mTie;
    private int mMove;

    private BorderPane mBorderPane;

    private Label mLabel;
    private Button mRock;
    private Button mPaper;
    private Button mCut;
    private Button mCheat;
    private ListView<String> mTries;
    private Label mCode;

    private Group mRoot;
    private VBox mButtons;
    private VBox mList;
   
    private EventHandler<ActionEvent> mSmash;
    private EventHandler<ActionEvent> mFold;
    private EventHandler<ActionEvent> mSnip;
    private EventHandler<ActionEvent> mPeak;

    private Scene mScene;

   public static void main(String[] args)
   {
       launch(args);
   }

   public void run()
   {
       Process p = null;
       try {
	   p = new ProcessBuilder("java", "ComputerFight").start();
       }
       catch (Exception e)
       {
       }
 
	while (VBoxGlory.getInstance().isRunning())
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
           }
        );

        play(primaryStage);

        primaryStage.show();
    }

    private void play(Stage primaryStage)
    {
        mCheater = false;
        mRoot = new Group();
        mScene = new Scene(mRoot, 300, 300);
        primaryStage.setScene(mScene);

        mSmash =
            new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                fight("R");
            }
        };

        mFold =
            new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                fight("P");
            }
        };

        mSnip =
            new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
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
        mTries.setPrefWidth(200);

        mRock = new Button("ROCK");
	mPaper = new Button("PAPER");
        mCut = new Button("SCISSORS");
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
        mButtons.setMargin(mCheat, new Insets(100, 10, 10, 10));
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
    }

    private void reactions()
    {
	String reaction = "";

       if (mWin == 12)
       {
	  reaction = "I will not play with you.";
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

       mTries.getItems().add(reaction);
    }

    private void check()
    {
	mMove = (int)Math.ceil(Math.random()*3);
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
