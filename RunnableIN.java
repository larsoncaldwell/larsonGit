import java.awt.event.*;
import javax.swing.*;

public class RunnableIN
   implements ActionListener
{
   JButton toStart;
   JList threadsIN;
   JLabel labelIN;

   private static RunnableIN cInstance = new RunnableIN();

   private RunnableIN()
   {
      toStart = new JButton("Start");
      threadsIN = new JList();
      labelIN = new JLabel("These are the input commands");
      toStart.addActionListener(this);
   }

   public static RunnableIN getInstance()
   {
      return cInstance;
   }
   
   public void actionPerformed(ActionEvent event)
   {
      toStart.setText("Its Begun!");
   }
}
