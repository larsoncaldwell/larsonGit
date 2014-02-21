import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.scene.control.SelectionMode;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.*;

public class RunnableNode
   extends VBox
{
   private Button mButton;
   private ListView<String> mList;
   private Label mLabel;

    public RunnableNode(String pLabelName, String pButtonName,
                        SelectionMode pSelectionMode, 
                        EventHandler<ActionEvent> pButtonEventHandler,
                        ObservableList<String> pListItems)
   {
      mButton = new Button(pButtonName);
      mButton.setOnAction(pButtonEventHandler);
      mList = new ListView<String>();

      mList.getSelectionModel().setSelectionMode(pSelectionMode);
      mList.setPrefWidth(200);
      mLabel = new Label(pLabelName);
      set();
   }

   public void set()
   {
      getChildren().add(mLabel);
      getChildren().add(mList);
      getChildren().add(mButton);

      setMargin(mButton, new Insets(0, 10, 25, 10));
      setMargin(mLabel, new Insets(10, 10, 0, 10));
      setMargin(mList, new Insets(20));
      setVgrow(mList, Priority.ALWAYS);
      setAlignment(Pos.CENTER);
   }

    public boolean push(String pApp)
    {
       if (inList(pApp))
       {
	  return false;
       }
       else
       {
          mList.getItems().add(pApp);
          mList.getSelectionModel().select(pApp);
          return true;
       }
    }

    public void pop(String pApp)
    {
	if (inList(pApp))
        {
	    mList.getItems().remove(pApp);
	}
    }

    public void popAll()
    {
	for (String eApp : new ArrayList<String>
            (mList.getSelectionModel().getSelectedItems()))
        {
	   pop(eApp);
	}
        mList.getSelectionModel().selectFirst();
    }

    public String getSelectedItem()
    {
       return mList.getSelectionModel().getSelectedItem();
    }

    public boolean inList(String pApp)
    {
	return mList.getItems().contains(pApp);
    }

    public void requestButtonFocus()
    {
	mButton.requestFocus();
    }
}
