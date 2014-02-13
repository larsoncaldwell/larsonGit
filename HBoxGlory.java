import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javafx.scene.layout.HBox;

public class HBoxGlory
   implements ActionListener
{
   JTextField enter;
   JLabel labelEnter;
   Runnable mRunable;
   RunnableThread mRunnableThread;
   
   public static void main(String[] args)
   {
      new HBoxGlory().run();
   }

   public void start()
   {
      mRunnableThread.add(mRunnable);
      while(mRunnable.isRunning())
      {
         //keep runnables running
      }
   }

   public void stop()
   {
      mRunnableThread.remove();
   }

   public void actionPerformed(ActionEvent event)
   {
       event.consume();
   }

   public void run()
   {
      JFrame frame = new JFrame();
      labelEnter = new JLabel("Enter Here:");
      
      HBox hbox = new HBox(10);
      hbox.getChildren().addAll(RunnableIN.getInstance(),
                                RunnableOUT.getInstance());

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setSize(400,500);

      frame.setVisible(true);
   }
}
