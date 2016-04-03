//encapsulates matchmaking and the individual game services, driven by comms and relays back to client through comms
//TODO add a static method that will apply a move to a specified game, waiting on game 
//TODO add a static method that will return the current game state of a specified game, waiting on game
import java.util.*;
public class GameServices implements Runnable{
	
	private final static int loopwait = 50;
	private Thread t;
	private String name;
	private static ArrayList<duck> line;
	private static ArrayList<String> games;
	//private static game[] games; waiting on game object
	
	GameServices(String reference){
		line = new ArrayList<duck>(0);
		games = new ArrayList<String>(0);
		name = reference;
	}
	
	//thread methods
	@Override
	public void run() {
		try {
			while(ServerComms.shutdown == false){
				synchronized (line) {
					Collections.sort(line);
					this.match();
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

	private void match(){
		try {
			synchronized (line) {
				Iterator<duck> iterator = line.iterator();
				duck prev = null;
				duck curr = null;
				duck goose = new duck(":", "9999999");
				ArrayList<String> toRemove = new ArrayList<String>(0);
				if (iterator.hasNext())
					prev = iterator.next();
				while (iterator.hasNext()) {
					curr = iterator.next();
					//logic for matching players subject to change
					if ((prev.rating - curr.rating) <= Integer.max(prev.wait, curr.wait)) {
						synchronized (games) {
							//TODO create game instance waiting on game object
							games.add(prev.name + curr.name);
							
						}
						//set players to be removed
						toRemove.add(prev.name);
						toRemove.add(curr.name);
						curr = goose;
					}
					prev = curr;
				}
				//remove all matched players from the line
				if(toRemove.size() > 0)
					for (int i = 0; i <= toRemove.size() - 1; i++)
						this.remove(toRemove.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	//user command methods
	public static String join(String username){
		try {
			//TODO get user rating from DBhandler waiting on DBhandler
			duck player = new duck(username, "100");
			if(player.rating < 0)
				return "error:invalid rating:"+"0";
			synchronized (line) {
				line.add(new duck(username, "0"));
			}
			return "success:join:" +username;
		} catch (Exception e) {
			System.out.println(e.toString());
			return "error:join:exception";
		}
	}
	
	public static String leave(String username){
		try{
			synchronized (line) {
				Iterator<duck> iterator = line.iterator();
				while(iterator.hasNext()){
					if (iterator.next().name == username){
						iterator.remove();
						return "success:leave:"+username;
					}
				}
				return "failed:leave:notinline:"+username;
			}
		} catch (Exception e) {
		System.out.println(e.toString());
		return "error:leave:exception";
		}
	}
	
	public static String inGame(String username){
		if (GameServices.getgames().contains(username))
			return "success:inGame:"+ username;
			return "failed:inGame:"+ username;
	}
	
	//internal and testing methods
	private void remove(String username){
		try{
			synchronized (line) {
				Iterator<duck> iterator = line.iterator();
				while(iterator.hasNext()){
					if (iterator.next().name == username){
						iterator.remove();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static String getline(){
		try{
			String out = "line";
			synchronized (line) {
				Iterator<duck> iterator = line.iterator();
				while(iterator.hasNext()){
					out = out + ":" +iterator.next().name;
				}
			}
			return out;
		} catch (Exception e) {
			System.out.println(e.toString());
			return "error:getline:exception";
		}
	}
	
	public static String getgames(){
		try{
			String out = "games";
			synchronized (games) {
				Iterator<String> iterator = games.iterator();
				while(iterator.hasNext()){
					out = out + ":" +iterator.next();
				}
			}
			return out;
		} catch (Exception e) {
			System.out.println(e.toString());
			return "error:getgames:exception";
		}
	}
}