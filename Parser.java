
public class Parser {

	public Parser(String input) {}
	
	public static String Execute(String input, boolean debug){
		try {
			String output = "";
			while(output.contains("::")){
				output.replace("::", ":");
			}
			String[] commandarr = input.split(":");
			//login command "login:username:password"
			if (commandarr[0] == "login"){
				if (commandarr.length < 3){
					output = "failed:" + commandarr[0] + ":command missing values";
				} else {
					Login loginInfo = new Login(commandarr[1],commandarr[2]);
					String loginResults = loginInfo.trylogin();
					output = loginResults;
				}
			}
			//account creation command "createaccount:username:password:email"
			else if (commandarr[0] == "createaccount"){
				if (commandarr.length < 4){
					output = "failed:" + commandarr[0] + ":command missing values";
				} else {
					Login accountInfo = new Login(commandarr[1],commandarr[2]);
					String accountResults = accountInfo.newaccount(commandarr[3]);
					output = accountResults;
				}
			}
			// template
			/*
			 * else if (commandarr[0] == "command"){
				if (commandarr.length < 0){
					output = "failed:" + commandarr[0] + ":command missing values";
				} else {
					output = result;
					if (debug){System.out.println("command returned: " + result);}
				}
			}
			*/
			//default action
			else{
				if (debug)
					System.out.println(commandarr[0] + " returned:" + output);
			}
			return output;
		} catch (Exception e) {
			System.out.print("parser error" + e.toString());
			return "error:parser";
		}
	}

}
