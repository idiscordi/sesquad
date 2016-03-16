//handles login functionality, driven by servercomms and speaks to dbhandler
import java.lang.String;
class login{
	private String password;
	private String username;
	
	public login(String user, String pass){
		password = pass;
		username = user;
	}
	
	public String trylogin(){
		String str = dbhandler.trylogin(username, password);
		return str;
	}
	
	public String newaccount(String emailadd){
		if (username.contains("swear"){return "failed:contains swear"}; 
		//future todo: check for explitives in username
		return dbhandler.newaccount(username, password, emailadd);
	}
}