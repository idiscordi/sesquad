//Server Communications handler
import java.io.*;
import java.net.*;

/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

//making class
class ServerComms
{
	public static void main (String args[])
	{
		//separate strings for clarity
		String incoming;
		String outbound;
		//to kill server
		boolean shutdown = false;
		
		//call stopComms(true); and server will quit shut off comms
		public void stopComms(boolean b)
		{
			shutdown = b;
		}
		
		try
		{
			ServerSocket listener = new ServerSocket(2000);
			//while im not ordered to shutdown
			while(!shutdown)
			{
				//blocking wait
				Socket incoming = listener.accept();
				
				InputStream dataIn = incoming.getInputStream();
				
			}
		}
	}
}