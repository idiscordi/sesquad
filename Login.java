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
	try{
		String str = DBHandler.DBLogin(username, password);
		return str;
	} catch (Exception e) {System.err.println("Exception Error");}
	}
	
	public String newaccount(String emailadd){
	try{
		if (username.contains("swear"){return "failed:contains swear"}; 
		//future todo: check for explitives in username
		return DBHandler.DBNewAcc(username, password, emailadd);
	} catch (Exception e) {System.err.println("Exception Error");}
	}
}