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
		System.out.println(GameServices.leave("evan"));
		System.out.println(GameServices.join("ffff"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.getgames());
		System.out.println(GameServices.join("gary"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("hary"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("ivan"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("jake"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("keel"));
		System.out.println(GameServices.getline());
		System.out.println(GameServices.join("lee"));
		System.out.println(GameServices.getline());
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(GameServices.getgames());
		System.out.println(GameServices.getline());
	}	
}
