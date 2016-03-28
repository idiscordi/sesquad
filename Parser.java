
public class Parser {

	public Parser(String input) {}
	
	public static String Execute(String input, boolean debug){
		try {
			String output = "";
			String[] commandarr = input.split(":");
			//login command "login:username:password"
			if (commandarr[0] == "login"){
				if ((commandarr[1].length() != 0) && (commandarr[2].length() != 0)){
					Login loginInfo = new Login(commandarr[1],commandarr[2]);
					String loginResults = loginInfo.trylogin();
					if (debug)
						System.out.println("login returned: " + loginResults);
					output = loginResults;
				} else {
					output = "failed:" + commandarr[0] + ":command missing values";
				}
			}
			//account creation commandarr "createaccount:username:password:email"
			else if (commandarr[0] == "createaccount"){
				if ((commandarr[1].length() != 0) && (commandarr[2].length() != 0) && (commandarr[3].length() != 0)){
					Login accountInfo = new Login(commandarr[1],commandarr[2]);
					String accountResults = accountInfo.newaccount(commandarr[3]);
					if (debug)
						System.out.println("account creation returned: " + accountResults);
					output = accountResults;
				} else {
					output = "failed:" + commandarr[0] + ":command missing values";
				}
			}
			//default action
			else{
				if (debug)
					System.out.println("input did not start with a valid command, started with: " + commandarr[0]);
			}
			return output;
		} catch (Exception e) {
			System.out.print("parser error" + e.toString());
			return "error:parser";
		}
	}

}
