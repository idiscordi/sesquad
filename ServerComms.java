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
			ServerSocket listener = new ServerSocket(55000);
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
					if ((commands[1].length != 0) && (commands[2].length != 0)){
						Login loginInfo = new Login(commands[1],commands[2]);
						String loginResults = loginfo.trylogin();
						if (debug)
							System.out.println("login returned: " + loginResults);
						output = loginresults;
					} else {
						output = "failed:" + commands[0] + ":command missing values";
					}
				}
				//account creation commands "createaccount:username:password:email"
				else if (commands[0] == "createaccount"){
					if ((commands[1].length != 0) && (commands[2].length != 0) && (commands[3].length != 0){
						Login accountInfo = new Login(commands[1],commands[2]);
						String accountResults = accountInfo.newaccount(commands[3]);
						if (debug)
							System.out.println("account creation returned: " + accountResults);
						output = accountResults;
					} else {
						output = "failed:" + commands[0] + ":command missing values";
					}
				}
				//default action
				else{
					if (debug)
						System.out.println("input did not start with a valid command, started with: " + commands[0]);
				}
				//TODO: wait on return message from service and reformat if necessary to send to client
				if (output.indexof("\n") != -1){
					output.replace("\n","");
				}
				//respond with something
				outputData.writeBytes("Server received your message as: " + input + "\n");
				if ((output != null) && (output != ""){
					outputData.writeBytes("Server returned the message: " + output + "\n");
				} else {
					outputData.writeBytes("Server returned nothing \n");
			}		
			
			client.close();
			listener.close();
		}
		catch(Exception e){ System.out.println(e.toString());}
	}
}
