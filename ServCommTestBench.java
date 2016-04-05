import java.io.*;
import java.net.*;

public class ServCommTestBench
{
	public static void main(String argv[]) throws Exception
	{
		String toSend;
		String received;
		
		Socket server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		BufferedReader incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		DataOutputStream outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//login command "login:username:password"
		toSend = "login:test:1234\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
	
		//join matchmaking command "findmatch:username"
		toSend = "findmatch:allen\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//join matchmaking command "findmatch:username"
		toSend = "findmatch:brenda\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//check if in game command "ingame:username"
		toSend = "ingame:allen\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms (expect fail)\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//wait to allow thread to catch up
		//check if in game command "ingame:username"
		try{Thread.sleep(250);}catch(Exception e){}
		toSend = "ingame:allen\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		toSend = "ingame:brenda\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		String[] ingamearr = received.split(":");
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		System.out.println("\ngot gid of " + ingamearr + "\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//get game state "getstate:username:gameid"
		
		toSend = "getstate:allen:0\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//make move "gamemove:username:gameid:xcord,ycord:xcord,ycord: ..."
		toSend = "gamemove:allen:0:1,1\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//make move "gamemove:username:gameid:xcord,ycord:xcord,ycord: ..."
		toSend = "gamemove:allen:0:2,1\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket(InetAddress.getByName("uaf132992.ddns.uark.edu"), 55000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		//make move "gamemove:username:gameid:xcord,ycord:xcord,ycord: ..."
		toSend = "gamemove:brenda:0:1,1\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
	}
}
