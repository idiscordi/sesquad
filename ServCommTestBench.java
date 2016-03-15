import java.io.*;
import java.net.*;

public class ServCommTestBench
{
	public static void main(String argv[]) throws Exception
	{
		String toSend;
		String received;
		
		Socket server_connection = new Socket("localhost", 2000);
		BufferedReader incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		DataOutputStream outbound = new DataOutputStream(server_connection.getOutputStream());
		
		toSend = "hello world\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket("localhost", 2000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
	
		toSend = "63217863178267dgsajhabdshm/sahuisadg78sa08ycanjk%#$@%(*(SD*\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket("localhost", 2000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		toSend = "testing this out\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket("localhost", 2000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		toSend = "   \n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket("localhost", 2000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		toSend = "you can send a blank\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket("localhost", 2000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		toSend = "\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
		server_connection = new Socket("localhost", 2000);
		incoming = new BufferedReader(new InputStreamReader(server_connection.getInputStream()));
		outbound = new DataOutputStream(server_connection.getOutputStream());
		
		toSend = "or simply a newline char\n";
		System.out.println("sending: " + toSend);
		outbound.writeBytes(toSend);
		received = incoming.readLine();
		System.out.println(received);
		System.out.println("\ngot back comms\n");
		server_connection.close();
		
	}
}
