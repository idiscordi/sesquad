import java.lang.String;
import java.io.*;


public class DBTest {

	
	public static void main(String[] args) 
	{
		String name;
		String pass;
		String email;
		String response;
		name = "test";
		pass = "1234";
		email = "no@thanks.com";
		
		System.out.println("Trying login with test:1234\n");
		response = DBHandler.DBLogin(name, pass);
		System.out.println("Handlers sends back: " response + "\n");
	}

}
