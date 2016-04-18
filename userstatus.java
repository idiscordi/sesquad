public class userstatus{

	public String username;
	public int idletime;
	
	public userstatus(String user){
		username = user;
		idletime = 0;
	}
	
	public boolean equals(String user){
		if (user.equals(username)){
			idletime = 0;
			return true;
		}
		return false;
	}
}
