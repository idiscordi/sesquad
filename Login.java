//handles login functionality, driven by servercomms and speaks to dbhandler
import java.util.*;

class Login implements Runnable{
	private static final int loopwait = 1000;
	private static final int maxidle = 300; 
	private static ArrayList<userstatus> active;
	private Thread t;
	private String name;
	
	public Login(String reference){
		active = new ArrayList<userstatus>(0);;
		name = reference;
	}
	
	//thread methods
	@Override
	public void run(){
		try {
			while(ServerComms.shutdown == false){
				synchronized (active) {
					this.itterate();
				}
				Thread.sleep(loopwait);
			}
		} catch (Exception e) {
			 System.out.println(e.toString());
		}		
	}
	
	public void start(){
		if (t == null){
			t = new Thread (this, name);
			t.start ();
		}
	}

	private void itterate(){
		try {
			Iterator<userstatus> iterator = active.iterator();
			userstatus curr = null;
			while(iterator.hasNext()){
				curr = iterator.next();
				if(curr.idletime > maxidle){
					iterator.remove();
					//DBHandler.DBLogout(curr.username);
				}
			}
			iterator = active.iterator();
			while(iterator.hasNext())
				iterator.next().idletime++;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//server methods
	public static String checklogin(String user){
		try{
			synchronized(active){
				Iterator<userstatus> iterator = active.iterator();
				while(iterator.hasNext()){
					if (iterator.next().equals(user))
						return "success";
				}
			}
			return "Failed:checklogin:"+user+":logged out";
		} catch (Exception e) {
			System.out.println(e);
			return "Failed:checklogin:exception";
		}
	}
	
	//client command methods
	public static String trylogin(String username, String password){
		try{
			String str = DBHandler.DBLogin(username, password);
			if (str.startsWith("success")){
				synchronized(active){
					active.add(new userstatus(username));
				}
			}
			return str;
		} catch (Exception e) {
			System.err.println("Exception Error");
			return "failed:trylogin:exception1";
		}
	}
	
	public static String newaccount(String username, String password, String emailadd){
		try{
			if (username.contains("swear")){
				return "failed:contains swear";
			} 
			//future todo: check for explitives in username
			String str = DBHandler.DBNewAcc(username, password, emailadd);
			if (str.startsWith("success")){
				synchronized(active){
					active.add(new userstatus(username));
				}
			}
			return str;
		} catch (Exception e) {
			System.err.println(e);
			return "failed:login:exception2";
		}
	}
	
	//internal and testing methods
	public static String getactive(){
		try{
			String out = "active";
			synchronized (active) {
				Iterator<userstatus> iterator = active.iterator();
				while(iterator.hasNext()){
					out = out + ":" +iterator.next().username;
				}
			}
			return out;
		} catch (Exception e) {
			System.out.println(e.toString());
			return "error:getactive:exception";
		}
	}

	public static void forceadd(String username){
		try {
			synchronized(active){
				active.add(new userstatus(username));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}