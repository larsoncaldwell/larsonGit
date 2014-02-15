import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Control;

public class RunnableNode
   extends Control
{
   private Button mButton;
   private ListView<String> mList;
   private Label mLabel;
   private String LName;
    private String BName;

   public RunnableNode(String pLName, String pBName)
   {
      mButton = new Button(pBName);
      mList = new ListView();
      mLabel = new Label(pLName);
   }
}
