//encapsulates matchmaking and the individual game services, driven by comms and relays back to client through comms
//TODO make the run() sort the array before finding matches
//TODO add a static method that removes a player from the matchmaking queue
//TODO add a static method that will apply a move to a specified game
//TODO add a static method that will return the complete game state of a specified game
import java.util.*;
public class GameServices implements Runnable {
	
	private static ArrayList<duck> line;
	private static ArrayList<String> games;
	//private static game[] games; waiting on game object
	
	@Override
	public void run() {
		try {
			line = null;
			games = null;
			while(ServerComms.shutdown == false){
				synchronized (line) {
					this.match();
				}
			}
		} catch (Exception e) {
			 System.out.println(e.toString());
		}
	}
	
	private void match(){
		Iterator<duck> iterator = line.iterator();
		duck prev = null;
		duck curr = null;
		if(iterator.hasNext())
			prev = iterator.next();
		while(iterator.hasNext()){
			curr = iterator.next();
			//logic for creating a game subject to change
			if((prev.rating - curr.rating) <= Integer.max(prev.wait, curr.wait)){
				synchronized (games){
				//TODO create game instance waiting on game object
				//TODO notify players of game id
				//TODO remove players from list
				}
			}
		}
	}

	public static String join(String username){
		try {
			//TODO get user rating from DBhandler waiting on DBhandler
			duck player = new duck(username, "100");
			if(player.rating < 0)
				return "error:invalid rating:"+"0";
			synchronized (line) {
				line.add(new duck(username, "0"));
			}
			return "success:" +username+ ":added to matchmaking queue";
		} catch (Exception e) {
			System.out.println(e.toString());
			return "error:exception:join";
		}
	}
}