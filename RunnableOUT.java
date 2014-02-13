import java.awt.event.*;
import javax.swing.*;

public class RunnableOUT
   implements ActionListener
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
