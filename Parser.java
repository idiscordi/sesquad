
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
			if ((commandarr[0] == "login") && (commandarr.length >= 3)){
				Login loginInfo = new Login(commandarr[1],commandarr[2]);
				String loginResults = loginInfo.trylogin();
				output = loginResults;
			}
			//account creation command "createaccount:username:password:email"
			else if ((commandarr[0] == "createaccount") && (commandarr.length >= 4)){
				Login accountInfo = new Login(commandarr[1],commandarr[2]);
				String accountResults = accountInfo.newaccount(commandarr[3]);
				output = accountResults;
			}
			//join matchmaking command "findmatch:username"
			else if ((commandarr[0] == "findmatch") && (commandarr.length >= 2)){
			//	output = matchmaking.join(commandarr[1]);		
			}
			//exit matchmaking command "leavematch:username"
			else if ((commandarr[0] == "leavematch") && (commandarr.length >= 2)){
			//	output = matchmaking.leave(commandarr[1]);
			}
			//get game state "getstate:username:gameid"
			else if ((commandarr[0] == "getstate") && (commandarr.length >= 3)){
			//	output = matchmaking.getstate(commandarr[2], commandarr[1]);
			}
			//make move "gamemove:username:gameid:xcord,ycord:xcord,ycord: ..."
			else if ((commandarr[0] == "gamemove") && (commandarr.length >= 4)){
			//	output = class.makemove(commandarr);
			}
			// template
			/*
		 	else if ((commandarr[0] == "command") && (commandarr.length >= 0)){
			//	output = class.request(commandarr[x])
			}
			*/
			//default action
			else{
				output = "error:" + commandarr[0] + ":is invalid or missing values";
			}
				if (debug)
					System.out.println(commandarr[0] + " returned:" + output);
			return output;
		} catch (Exception e) {
			System.out.print("parser error" + e.toString());
			return "error:exception";
		}
	}

}
