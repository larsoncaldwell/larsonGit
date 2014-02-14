import java.awt.event.*;
import javax.swing.*;
import javafx.scene.Node;

public class Runnable
   implements Node
{
   private JButton mButton;
   private JList mList;
   private JLabel mLabel;
   private String LName;
   private String BName;

   private static Runnable cInstance = new Runnable();

   public Runnable(String pLName, String pBName)
   {
      mButton = new JButton(pBName);
      mList = new JList();
      mLabel = new JLabel(pLName);
   }

   public static Runnable getInstance()
   {
      return cInstance;
   }
}
