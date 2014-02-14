import java.io.File;
import java.util.Timer;
import java.swing.*;

public class Racer
    extends Thread 
{
   private int mFileNumber;
   private boolean mTimeToDelete;
   private Thread mThreadRunner;
   private Thread mThreadDeleter;
   private Thread mThreadMonitor;
   
   public Racer()
   {
      mFileNumber = 0;
      mThreadRunner = new Thread();
      mThreadDeleter = new Thread();
      mThreadMonitor = new Thread();

      mThreadRunner.start();
      mThreadDeleter.start();
      mThreadMonitor.start();
   }
   
   public File newFileN(int number)
   {
      return new File(String.format("file.%05d", number));
   }
   
   public void run()
   {
      while (true)
      {
          //TimerTask timer = new TimerTask()
	      //{
             
	      //};
	  //new Timer().schedule(timer, 1000);

         try
	 {
	    if (!(mTimeToDelete) && (newFileN(mFileNumber).createNewFile()))
	    {
	       mFileNumber++;
            }
            else if (newFileN(mFileNumber).delete())
	    {
	       mFileNumber++;
	    }
	    Thread.sleep(250);
	 }
         catch (Exception e)
	 {
         }
      }
   }
   
   public static void main(String[] args)
   {
      new Racer().start();
   }
}
