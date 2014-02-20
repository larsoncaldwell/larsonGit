import java.util.*;


public class HelloApp
  implements Runnable
	     //extends Thread
{
   public void run()
   {
       while (VBoxGlory.getInstance().isRunning())
      {
         try 
         {
	    System.out.println("Hello everyone");
            Thread.sleep(1000); 
         }
         catch (Exception e)
         {
	     System.out.println("ERROR");
	 }
      }
   }
}