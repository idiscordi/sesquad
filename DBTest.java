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
		System.out.println("Handlers sends back: " + response + "\n");

		System.out.println("Trying to create with with test:1234:no@thanks.com\n");
		response = DBHandler.DBNewAcc(name, pass, email);
		System.out.println("Handlers sends back: " + response + "\n");

		email = "testmail@test.com";
		System.out.println("Trying to create with with test:1234:testmail@test.com\n");
		response = DBHandler.DBNewAcc(name, pass, email);
		System.out.println("Handlers sends back: " + response + "\n");

		name = "joe";
		pass = "joedog";
		email = "joe@gmail.com";

		System.out.println("Trying to create with with joe:joedog:joe@gmail.com\n");
		response = DBHandler.DBNewAcc(name, pass, email);
		System.out.println("Handlers sends back: " + response + "\n");
	}

}