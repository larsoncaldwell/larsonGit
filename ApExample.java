import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;

public class ApExample
   extends Application
{
    Button mButton = new Button("Hello");

    public static void main(String[] args)
    {
	launch(args);
    }

    public void start(final Stage primaryStage)
    {
        BorderPane theBorder = new BorderPane();
        theBorder.setCenter(mButton);
        Scene theScene = new Scene(theBorder, 200, 500);
        primaryStage.setScene(theScene);
	primaryStage.setTitle("This is a test");
        primaryStage.show();
    }
}