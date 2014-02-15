import java.io.File;
import javax.swing.*;
import java.awt.event.*;

public class Racer
    extends Thread 
    implements ActionListener
{
   private int mFileNumber;
   private boolean mCreating;
   private Thread mThreadRunner;
   private Thread mThreadDeleter;
   private FileToucher mThreadMonitor;
   
   public Racer()
   {
      mFileNumber = 0;
      mThreadRunner = new Racer(true);
      mThreadDeleter = new Racer(false);
      mThreadMonitor = new OutputingFileToucher();

      startRace();
   }

   public Racer(boolean pCreating)
   {
      mCreating = pCreating;
   }

   public void startRace()
   {
       mThreadRunner.start();
       Thread.sleep(500);
       mThreadDeleter.start();
       new Timer(1000, this).start();
   }
   
   public File newFileN(int number)
   {
      return new File(String.format("file.%05d", number));
   }
   
   public void run()
   {
      while (true)
      {
         try
	 {
	    if ((mCreating && newFileN(mFileNumber).createNewFile())
                 || (!(mCreating) && NewFileN(mFileNumber).delete()))
	    {
	       mFileNumber++;
	    }
	 }
         catch (Exception e)
	 {
	     System.out.println("never call me");
         }
      }
   }

   public void actionPerformed(ActionEvent e)
   {
       System.out.println("oh no");
   }

   public static void main(String[] args)
   {
      new Racer().start();
   }
}
