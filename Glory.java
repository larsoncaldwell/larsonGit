import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Glory
 implements ActionListener
{
   JButton button;
   JTextField enter;
   
   public static void main(String[] args)
   {
      new Glory().run();
   }

   public void run()
   {
      JFrame frame = new JFrame();
      button = new JButton("Start");
      enter = new JTextField(20);
      enter = new JTextField("Enter Here:");

      frame.getContentPane().add(BorderLayout.SOUTH, button);
      frame.getContentPane().add(BorderLayout.NORTH, enter);

      button.addActionListener(this);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //frame.getContentPane().add(button);
      //frame.getContentPane().add(enter);

      frame.setSize(400,500);

      frame.setVisible(true);
   }

   public void actionPerformed(ActionEvent event)
   {
      button.setText("Its Begun!");
   }
}
