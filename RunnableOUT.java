import java.awt.event.*;
import javax.swing.*;
import org.openide.nodes.Node;

public class RunnableOUT
   extends Node
{
   JButton toStop;
   JList threadsOUT;
   JLabel labelOUT;

   private static RunnableOUT cInstance = new RunnableOUT();

   private RunnableOUT()
   {
      toStop = new JButton("Stop");
      threadsOUT = new JList();
      labelOUT = new JLabel("These are the running applications");
      toStop.addActionListener(this);
   }

   public static RunnableOUT getInstance()
   {
      return cInstance;
   }
   
   public void actionPerformed(ActionEvent event)
   {
      toStop.setText("Its Over!");
   }
}
