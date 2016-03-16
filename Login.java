//handles login functionality, driven by servercomms and speaks to dbhandler
import java.lang.String;
class Login{
	private String password;
	private String username;
	
	public Login(String user, String pass){
		password = pass;
		username = user;
	}
	
	public String trylogin(){
		String str = DBHandler.trylogin(username, password);
		return str;
	}
	
	public String newaccount(String emailadd){
		if (username.contains("swear"){return "failed:contains swear"}; 
		//future todo: check for explitives in username
		return DBHandler.newaccount(username, password, emailadd);
	}
}