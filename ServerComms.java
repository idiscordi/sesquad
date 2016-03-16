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
	static boolean shutdown = false;
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
			ServerSocket listener = new ServerSocket(2000);
			//while im not ordered to shutdown
			while(!shutdown)
			{
				//some empty strings and a blocking wait
				String input = "";
				String output = "";
				Socket client = listener.accept();
				String[] commands = null;
				
				
				//linking up w/ safe reader for instream and getting access to outstream
				BufferedReader inputData = new BufferedReader(new InputStreamReader(client.getInputStream()));
				DataOutputStream outputData = new DataOutputStream(client.getOutputStream());
				
				//get some data
				input = inputData.readLine();
				if(debug && input != null)
					System.out.println("Received: " + input + " from client.");
				
				//TODO: parse message and pass to appropriate service if valid
				//todo: return info to sender(may be added to servercomms or login)
				//break input into an array
				commands = input.split(":");
				//login command "login:username:password"
				if (commands[0] == "login"){
					Login loginInfo = new Login(commands[1],commands[2]);
					String loginResults = loginfo.trylogin();
					if (debug)
						System.out.println("login returned: " + loginResults);
				//account creation commands "createaccount:username:password"
				else if (commands[0] == "createaccount")
					Login accountInfo = new Login(commands[1],commands[2]);
					String accountResults = accountInfo.newaccount();
					if (debug)
						System.out.println("account creation returned: " + loginResults);
				}
				//default action
				else{
					if (debug)
						System.out.println("input did not start with a valid command, started with: " + commands[0]);
				}
				//TODO: wait on return message from service and reformat if necessary to send to client
				
				//respond with something
				outputData.writeBytes("Server received your message as: " + input + "\n");
				
			}
			listener.close();
		}
		catch(Exception e){ System.out.println(e.toString());}
	}
}
