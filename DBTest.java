import java.lang.String;
import java.io.*;


public class DBTest {

	
	public static void main(String[] args) 
	{
		String name;
		String pass;
		String email;
		name = "test";
		pass = "1234";
		email = "no@thanks.com";
		
		DBHandler.DBLogin(name, pass);
	}

}
