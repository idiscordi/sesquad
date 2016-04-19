import java.lang.String;
import java.io.*;

public class Game{
	private char[][] grid = new char[3][3];
	private String user1;
	private String user2;
	private String gid;
	//boolean toggle, 2 player game, any more is a waste of memory
	public boolean p1turn = true;
	//private String[] commandArr;
	
	public Game(String username1, String username2, String gameid){
		//Method to keep track of players
		user1 = username1;
		user2 = username2;
		gid = gameid;
	}
	
	public String getGameID()
	{
		return gid;
	}
	
	public String getPlayerOne()
	{
		return user1;
	}
	
	public String getPlayerTwo()
	{
		return user2;
	}
	
	//call this to receive a return string to pass back to client in format of
	//gamedata:gameID:player1username:player2username:currentplayerturn
	public String getGameData()
	{
		String temp;
		
		if(p1turn)
			temp = "p1";
		else
			temp = "p2";
		
		return ("gamedata:" + gid + ":" + user1 + ":" + user2 + ":" + temp + "\n");
	}
	
	/*public int getNextMove(int turn){
		//Method to Determine gamemoves
		turn = 1;
		turn = turn * -1; // Each turn gives a player 1, -1, 1,...
	}*/
	
	//this method seems unnecessary for the current needs, see validateMove()
	/* public void putPiece(int turn, char[][] grid) 
	  {
	 
		//Method to take turns/ put pieces
		turn = 1;
		//turn = turn * -1;
		
		char P1piece ='O';
		char P2piece = 'X';
		
		if (turn == 1) {
			P1piece = 'O';
			turn = turn * -1;
		}
		else {
			P2piece = 'X';
			turn = turn * -1;
		}
	} /*
	
	
	//dont think this is needed either, plus turn would be destroyed after each run
	/*
	public String putPieceV2(int turn) 
	{
		//Edit method, char[][] grid may have issues
		turn = 1;
	
		if (turn == 1) 
		{
			turn = turn * -1;
			return "user1 turn";
			
		}
		else 
		{
			turn = turn * -1;
			return "user2 turn";
			
		}
	} */
	
	//handles move validation.  if valid places token into array and returns success
	public String validateMove(String[] gamemove) 
	{
		String temp = gamemove[1];
		String[] tempArr = temp.split(",");
		int x = Integer.parseInt(tempArr[0]);
		int y = Integer.parseInt(tempArr[1]);
		
		if ((x < 0 || x > 2) || (y < 0 || y > 2)) 
		{
			return "failed:" + gid + ":invalid move\n";
		}
		//Check if spot is taken
		else if (grid[x][y] == 'O' || grid[x][y] == 'X')
		{
			return "failed:" + gid + ":invalid move\n";
		}
		else if (p1turn)
		{
			
				grid[x][y] = 'O';
				p1turn = !p1turn;
		}
		else
		{
			grid[x][y] = 'X';
			p1turn = !p1turn;
		}

		return "success:" + gid + ":move complete\n";
		
	}
	
	public String GameLogic()
	{
		//HORIZONTAL CHECKING FOR PLAYER 1
		if (p1turn && ((grid[0][0] == 'O') && (grid[0][1] == 'O') && (grid[0][2] == 'O')))
		{
			return "Gameover:" + user1 + "Wins!\n";
		}
		
		if (p1turn && ((grid[1][0] == 'O') && (grid[1][1] == 'O') && (grid[1][2] == 'O')))
		{
			return "Gameover" + user1 + "Wins!\n";
		}
		
		if (p1turn && ((grid[2][0] == 'O') && (grid[2][1] == 'O') && (grid[2][2] == 'O')))
		{
			return "Gameover:" + user1 + "Wins!\n";
		}
		
		
		
		//VERTICAL CHECKING FOR PLAYER 1
		if (p1turn && ((grid[0][0] == 'O') && (grid[1][0] == 'O') && (grid[2][0] == 'O')))
		{
			return "Gameover:" + user1 + "Wins!\n";
		}
		
		if (p1turn && ((grid[0][1] == 'O') && (grid[1][1] == 'O') && (grid[2][1] == 'O')))
		{
			return "Gameover" + user1 + "Wins!\n";
		}
		
		if (p1turn && ((grid[0][2] == 'O') && (grid[1][2] == 'O') && (grid[2][2] == 'O')))
		{
			return "Gameover:" + user1 + "Wins!\n";
		}
		
		
		
		//DIAGONAL CHECKING FOR PLAYER 1
		//TOP LEFT TO BOTTOM RIGHT
		if (p1turn && ((grid[0][0] == 'O') && (grid[1][1] == 'O') && (grid[2][2] == 'O')))
		{
			return "Gameover:" + user1 + "Wins!\n";
		}
		//TOP RIGHT TO BOTTOM LEFT
		if (p1turn && ((grid[0][2] == 'O') && (grid[1][1] == 'O') && (grid[2][0] == 'O')))
		{
			return "Gameover:" + user1 + "Wins!\n";
		}
		
		
		
		//HORIZONTAL CHECKING FOR PLAYER 2
		if (!p1turn && ((grid[0][0] == 'X') && (grid[0][1] == 'X') && (grid[0][2] == 'X')))
		{
			return "Gameover:" + user2 + "Wins!\n";
		}
		
		if (!p1turn && ((grid[1][0] == 'X') && (grid[1][1] == 'X') && (grid[1][2] == 'X')))
		{
			return "Gameover" + user2 + "Wins!\n";
		}
		
		if (!p1turn && ((grid[2][0] == 'X') && (grid[2][1] == 'X') && (grid[2][2] == 'X')))
		{
			return "Gameover:" + user2 + "Wins!\n";
		}
		
		
		
		//VERTICAL CHECKING FOR PLAYER 2
		if (!p1turn && ((grid[0][0] == 'X') && (grid[1][0] == 'X') && (grid[2][0] == 'X')))
		{
			return "Gameover:" + user2 + "Wins!\n";
		}
		
		if (!p1turn && ((grid[0][1] == 'X') && (grid[1][1] == 'X') && (grid[2][1] == 'X')))
		{
			return "Gameover" + user2 + "Wins!\n";
		}
		
		if (!p1turn && ((grid[0][2] == 'X') && (grid[1][2] == 'X') && (grid[2][2] == 'X')))
		{
			return "Gameover:" + user2 + "Wins!\n";
		}
		
		
		//DIAGONAL CHECKING FOR PLAYER 2
		//TOP LEFT TO BOTTOM RIGHT
		if (!p1turn && ((grid[0][0] == 'X') && (grid[1][1] == 'X') && (grid[2][2] == 'X')))
		{
			return "Gameover:" + user2 + "Wins!\n";
		}
		//TOP RIGHT TO BOTTOM LEFT
		if (!p1turn && ((grid[0][2] == 'X') && (grid[1][1] == 'X') && (grid[2][0] == 'X')))
		{
			return "Gameover:" + user2 + "Wins!\n";
		}
		
		
		
		//IF PREVIOUS CASES FAIL, GAME ENDS IN A TIE
		else
		{
			return "Gameover:" + user1 + "and" + user2 + "Tied!\n";
		}
	}
	
}