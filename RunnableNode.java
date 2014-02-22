import java.util.*;

import javafx.collections.ObservableList;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * This class is a VBox that contains a button, label and list, as well as
 * their properties.  This will be used in the RunnableHost class as the list
 * of input and running runnables.
 *
 * @author Larson Caldwell
 */

public class RunnableNode
   extends VBox
{
   //////////////////// private variables

   /**
    * The button (START or STOP).
    */
   private Button mButton;

   /**
    * The list containing the runnable names.
    */
   private ListView<String> mList;

   /**
    * The label for the unit.
    */
   private Label mLabel;

   //////////////////// Constructor

   /**
    * The constructor will set the properties of each element in the VBox.
    *
    * @param the label text
    * @param the button text
    * @param the mode of selection
    * @param the event handler for the button
    */
   public RunnableNode(String pLabelName, String pButtonName,
                       SelectionMode pSelectionMode, 
                       EventHandler<ActionEvent> pButtonEventHandler,
                       ObservableList<String> pListItems)
   {
      // the button is set up with a text and event handler
      mButton = new Button(pButtonName);
      mButton.setOnAction(pButtonEventHandler);

      // the list is given a size and selection mode (multiple or single)
      mList = new ListView<String>();
      mList.getSelectionModel().setSelectionMode(pSelectionMode);
      mList.setPrefWidth(200);

      // label is given text
      mLabel = new Label(pLabelName);

      // all the elements are added onto the VBox
      getChildren().add(mLabel);
      getChildren().add(mList);
      getChildren().add(mButton);

      // elements are given spacing and resizing properties
      setMargin(mButton, new Insets(0, 10, 25, 10));
      setMargin(mLabel, new Insets(10, 10, 0, 10));
      setMargin(mList, new Insets(20));
      setVgrow(mList, Priority.ALWAYS);
      setAlignment(Pos.CENTER);
   }

   //////////////////// Methods
   
   /**
    * A runnable is pushed onto the list.
    *
    * @param the name of the runnable
    *
    * @return whether or not the runnable is already on the list
    */
   public boolean push(String pApp)
   {
      // checks to see if the runnable is already on the list
      if (inList(pApp))
      {
         return false;
      }
      else
      {
         mList.getItems().add(pApp);
         // new item is auto-selected
         mList.getSelectionModel().select(pApp);
         return true;
      }
   }

   /**
    * A runnable is removed from the list.
    *
    * @param the name of the runnable
    */
   public void pop(String pApp)
   {
      if (inList(pApp))
      {
          mList.getItems().remove(pApp);
      }
   }

   /**
    * All of the selected runnables are removed from the list.
    */
   public void popAll()
   {
      // an enhanced loop is used to access each member in the list
      for (String eApp : new ArrayList<String>
          (mList.getSelectionModel().getSelectedItems()))
      {
         // pop is called on each member
         pop(eApp);
      }
      // first member is auto-selected
      mList.getSelectionModel().selectFirst();
   }

   /**
    * The selected item is retrieved.
    *
    * @return name of selected member
    */
   public String getSelectedItem()
   {
      return mList.getSelectionModel().getSelectedItem();
   }

   /**
    * Finds out if the application is in the list.
    *
    * @return whether or not it is in the list
    */
   public boolean inList(String pApp)
   {
      return mList.getItems().contains(pApp);
   }

   /**
    * Sets a focus on the button.
    */
   public void requestButtonFocus()
   {
      mButton.requestFocus();
   }
}
