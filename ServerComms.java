//Server Communications handler
import java.io.*;
import java.net.*;

//turn me on to get console messages, off to kill verbose, could do this through CLI args later
//e.g. java ServerComms would run without console printlns, java ServerComms -v would be verbose mode w/ outputs
boolean debug = true;
/*
 * This is currently a single connection w/ blocking wait
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
		
		
		//TODO: Accept many communications, this is single blocking right now
		try
		{
			ServerSocket listener = new ServerSocket(2000);
			//while im not ordered to shutdown
			while(!shutdown)
			{
				//some empty strings and a blocking wait
				String input = "";
				String output = "";
				Socket client = listener.accept();
				
				//linking up w/ safe reader for instream and getting access to outstream
				BufferedReader inputData = new BufferedREader(new InputStreamReader(client.getInputStream()));
				DataOutputStream outputData = new DataOutputStream(client.getOutPutStream());
				
				//get some data
				input = inputData.readline();
				if(debug)
					System.out.println("Received: " + input + "\n from client.");
				
				//TODO: parse message and pass to appropriate service if valid
				
				//TODO: wait on return message from service and reformat if necessary to send to client
				
				//respond with something
				outputData.writeBytes("Server received your message as: " + input);
				
			}
		}
	}
}