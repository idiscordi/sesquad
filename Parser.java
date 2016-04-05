
public class Parser {

	public Parser(String input) {}
	
	public static String Execute(String input){
		try {
			String output = "";
			while(input.contains("::")){
			 	input.replace("::", ":");
			}
			String[] commandarr = input.split(":");
			//login command "login:username:password"
			if ((commandarr[0].equals("login")) && (commandarr.length >= 3)){
				Login loginInfo = new Login(commandarr[1],commandarr[2]);
				String loginResults = loginInfo.trylogin();
				output = loginResults;
			}
			//account creation command "createaccount:username:password:email"
			else if ((commandarr[0].equals("createaccount")) && (commandarr.length >= 4)){
				Login accountInfo = new Login(commandarr[1],commandarr[2]);
				String accountResults = accountInfo.newaccount(commandarr[3]);
				output = accountResults;
			}
			//join matchmaking command "findmatch:username"
			else if ((commandarr[0].equals("findmatch")) && (commandarr.length >= 0)){
				output = GameServices.join(commandarr[1]);		
			}
			//exit matchmaking command "leavematch:username"
			else if ((commandarr[0].equals("leavematch")) && (commandarr.length >= 2)){
				output = GameServices.leave(commandarr[1]);
			}
			//check if in game command "ingame:username"
			else if ((commandarr[0].equals("ingame")) && (commandarr.length >= 2)){
				output = GameServices.inGame(commandarr[1]);		
			}
			//get game state "getstate:username:gameid"
			else if ((commandarr[0].equals("getstate")) && (commandarr.length >= 3)){
			//	output = GameServices.getstate(commandarr[2], commandarr[1]);
			}
			//make move "gamemove:username:gameid:xcord,ycord:xcord,ycord: ..."
			else if ((commandarr[0].equals("gamemove")) && (commandarr.length >= 4)){
				output = GameServices.gameMove(commandarr[1], commandarr[2], commandarr[3]);
			}
			// template
			/*
		 	else if ((commandarr[0] == "command") && (commandarr.length >= 0)){
			//	output = class.request(commandarr[x])
			}
			*/
			//default action
			else{
				output = "error:invalid or missing values:" + commandarr[0];
			}
			if (ServerComms.debug)
				System.out.println(commandarr[0] + " returned:" + output);
			return output;
		} catch (Exception e) {
			System.out.print("parser error" + e.toString());
			return "error:exception";
		}
	}

}
