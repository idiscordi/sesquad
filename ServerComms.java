//Server Communications handler
import java.io.*;
import java.net.*;



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
	//to kill server
	public static boolean shutdown = false;
	//e.g. java ServerComms would run without console printlns, java ServerComms -v would be verbose mode w/ outputs
	//turn me on to get console messages, off to kill verbose, could do this through CLI args later
	static boolean debug = true;
	
	//call stopComms(true); and server will quit shut off comms
	public void stopComms(boolean b)
	{
		shutdown = b;
	}
	
	
	public static void main (String args[])
	{
		//TODO: Accept many communications, this is single blocking right now
		try
		{
			GameServices matchmaking = new GameServices("test");
			matchmaking.start();
			
			ServerSocket listener = new ServerSocket(55000);
			//while im not ordered to shutdown
			while(!shutdown)
			{
				//some empty strings and a blocking wait
				String input = "";
				String output = "";
				Socket client = listener.accept();
				//String[] commands = null;
				
				
				//linking up w/ safe reader for instream and getting access to outstream
				BufferedReader inputData = new BufferedReader(new InputStreamReader(client.getInputStream()));
				DataOutputStream outputData = new DataOutputStream(client.getOutputStream());
				
				//get some data
				input = inputData.readLine();
				if(debug && input != null)
					System.out.println("Received: " + input + " from client.");
				
				//pass info to parser
				output = Parser.Execute(input);
				
				//TODO: wait on return message from service and reformat if necessary to send to client
				if (output.indexOf("\n") != -1){
					output.replace("\n","");
				}
				//respond with something
				//outputData.writeBytes("Server received your message as: " + input + "\n");
				if ((output != null) && (output != "")){
					outputData.writeBytes(output + "\n");
				} else {
					outputData.writeBytes("Server returned nothing \n");
				}
				//moved from outside the while loop.  had 0 chance of firing without shutdown signal	
				client.close();
			}		
			
			
			listener.close();
		}
		catch(Exception e){ System.out.println(e.toString());}
	}
}
