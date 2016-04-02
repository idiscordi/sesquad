public class GameServicesTB {

	static boolean debug = true;
	
	public static void main(String[] args) {
		
		GameServices matchmaking = new GameServices("test");
		matchmaking.start();
		
		System.out.println(GameServices.join("ana"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.leave("ana"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("bob"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("carl"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("dave"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.getgames());
		System.out.println(GameServices.join("evan"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("ffff"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.getgames());
		
		
	}

}
