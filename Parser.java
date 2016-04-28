public class Parser {
	
	public static String Execute(String input){
		try {
			String output = "";
		//	while(input.contains("::")){
			 //	input.replace("::", ":");
		//	}
			if(input == null) return "failed:DONT GIVE ME A NULL STRING THX";
			String[] commandarr = input.split(":");
			
			
			//login command "login:username:password"
			if ((commandarr[0].equals("login")) && (commandarr.length >= 3)){
				output = Login.trylogin(commandarr[1], commandarr[2]);
			}
			//account creation command "createaccount:username:password:email"
			else if ((commandarr[0].equals("createaccount")) && (commandarr.length >= 4)){
				output = Login.newaccount(commandarr[1],commandarr[2],commandarr[3]);
			}
			//join matchmaking command "findmatch:username"
			else if ((commandarr[0].equals("findmatch")) && (commandarr.length >= 2) && (Login.checklogin(commandarr[1]).startsWith("success"))){
				output = GameServices.join(commandarr[1]);		
			}
			//exit matchmaking command "leavematch:username"
			else if ((commandarr[0].equals("leavematch")) && (commandarr.length >= 2) && (Login.checklogin(commandarr[1]).startsWith("success"))){
				output = GameServices.leave(commandarr[1]);
			}
			//check if in game command "ingame:username"
			else if ((commandarr[0].equals("ingame")) && (commandarr.length >= 2) && (Login.checklogin(commandarr[1]).startsWith("success"))){
				output = GameServices.inGame(commandarr[1]);		
			}
			//get game state "getstate:username:gameid"
			else if ((commandarr[0].equals("getstate")) && (commandarr.length >= 3) && (Login.checklogin(commandarr[1]).startsWith("success"))){
			//	output = GameServices.getstate(commandarr[2], commandarr[1]);
			}
			//make move "gamemove:username:gameid:xcord,ycord:xcord,ycord: ..."
			else if ((commandarr[0].equals("gamemove")) && (commandarr.length >= 4) && (Login.checklogin(commandarr[1]).startsWith("success"))){
				output = GameServices.gameMove(commandarr[1], commandarr[2], commandarr[3]);
			}
			//get user data "getdatabyuser:username"
			else if ((commandarr[0].equals("getdatabyuser")) && (commandarr.length >= 2) && (Login.checklogin(commandarr[1]).startsWith("success"))){
				output = DBHandler.getDataByUser(commandarr[1]);
			}
			//get user data "getGamedata:username"
			else if ((commandarr[0].equals("getGameData")) && (commandarr.length >= 2) && (Login.checklogin(commandarr[1]).startsWith("success"))){
				output = GameServices.getGameData(commandarr[1]);
			}
			/*
			//Description
		 	else if ((commandarr[0].equals("command")) && (commandarr.length >= x) && (Login.checklogin(commandarr[1]).startsWith("success"))){
				output = class.request(commandarr[x])
			}
			*/
			//default actions
			else if((commandarr.length >= 2) && (Login.checklogin(commandarr[1]).startsWith("failed"))){
				output = Login.checklogin(commandarr[1]);
			}
			else{
				output = "failed:parser:invalid or missing values:" + commandarr[0];
			}
			if (ServerComms.debug)
				System.out.println(commandarr[0] + " returned:" + output);
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("parser error" + e.toString());
			return "failed:exception";
		}
	}

}
