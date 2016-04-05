
public class parsertb {

	public static void main(String[] args) {
    GameServices matchmaking = new GameServices("test");
    matchmaking.start();

    String test1 = "findmatch:Allen";
    String test2 = "findmatch:Zoe";
    System.out.println(Parser.Execute(test1));
    System.out.println(Parser.Execute(test2));

	
    try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
;
    String temp = Parser.Execute("ingame:Allen");
    String temp2 = Parser.Execute("ingame:Zoe");
    String[] tempArr = temp.split(":");
    String[] tempArr2 = temp2.split(":");
    System.out.println(Parser.Execute("gamemove:Allen:" + tempArr[3] + ":1,1"));
    System.out.println(Parser.Execute("gamemove:Zoe:" + tempArr2[3] + ":1,1"));
    System.out.println(Parser.Execute("gamemove:Zoe:" + tempArr2[3] + ":2,1"));
    System.out.println(Parser.Execute("gamemove:Allen:" + tempArr[3] + ":0,1"));
	}
}