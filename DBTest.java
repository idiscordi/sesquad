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
		
		if(DBHandler.incrementTotalGamesByUser("joe"))
		{
			System.out.println("Incremented Joe's total games by 1\n");
		}
		else
			System.out.println("Failed on: Incrementing Joe's total games by 1\n");
		
		if(DBHandler.incrementWinsByUser("joe"))
		{
			System.out.println("Incremented Joe's wins by 1\n");
		}
		else
			System.out.println("Failed on: Incrementing Joe's win by 1\n");
		
		if(DBHandler.setRankingByUser("joe", 37))
		{
			System.out.println("Set Joe's ranking to 37\n");
		}
		else
			System.out.println("Failed on: Set Joe's ranking to 37\n");
		
		if(DBHandler.toggleOnline("test"))
		{
			System.out.println("Toggled online status on/off for test (should be off now)\n");
		}
		else
			System.out.println("Failed on: Toggled online status on/off for test (should be off now)\n");

	}

}
