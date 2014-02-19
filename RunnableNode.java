import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;

public class RunnableNode
   extends VBox
{
   private Button mButton;
   private ListView<String> mList;
   private Label mLabel;

   public RunnableNode(String pLabelName, String pButtonName)
   {
      mButton = new Button(pButtonName);
      mList = new ListView();
      mLabel = new Label(pLabelName);
      set();
   }

   public void set()
   {
      getChildren().add(mLabel);
      getChildren().add(mList);
      getChildren().add(mButton);

      setMargin(mButton, new Insets(10, 10, 10, 100));
      setMargin(mLabel, new Insets(10, 10, 10, 100));
      setMargin(mList, new Insets(10));
      setVgrow(mList, Priority.ALWAYS);
   }
}
