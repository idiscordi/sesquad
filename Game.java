import java.lang.String;
import java.io.*;

public class Game{
	private char[][] grid = new char[3][3];
	private String user1;
	private String user2;
	private String gid;
	
	public Game(String username1, String username2, String gameid){
		//Method to keep track of players
		user1 = username1;
		user2 = username2;
		gid = gameid;
	}
	
	/*public int getNextMove(int turn){
		//Method to Determine gamemoves
		turn = 1;
		turn = turn * -1; // Each turn gives a player 1, -1, 1,...
	}*/
	
	public void putPiece(int turn, char[][] grid) {
		//Method to take turns/ put pieces
		turn = 1;
		//turn = turn * -1;
		
		char P1piece ='';
		char P2piece = '';
		
		if (turn == 1) {
			P1piece = 'O';
			turn = turn * -1;
		}
		else {
			P2piece = 'X';
			turn = turn * -1;
		}
	}
	
	public String putPieceV2(int turn) {
		//Edit method, char[][] grid may have issues
		turn = 1;
	
		if (turn == 1) {
			return "user1 turn";
			turn = turn * -1;
		}
		else {
			return "user2 turn";
			turn = turn * -1;
		}
	}
	
	public String ValidMove(String gamemove) {
		//Need to figure out how to check for Boundaries
		//Named x,y but maybe undeclare variables (New name needed)
		if ((x < 0 && x > grid.length) && (y < 0 && y > grid.length) {
			return "failed:Out of Bounds";
		}
		else {
			return "success:move complete";
		}
	}
}