import java.util.*;


public class HelloApp
   implements Runnable
{
   public void run()
   {
       VBoxGlory host = new VBoxGlory();
       while (host.isRunning(this))
      {
         try 
         {
            Thread.sleep(100); 
            System.out.println("Hello everyone");
         }
         catch (Exception e)
         {
	     System.out.println("ERROR");
	 }
      }
   }
}