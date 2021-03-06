import java.lang.String;
import java.io.*;
import java.lang.Math;

public class Game{
	private char[][] grid = new char[3][3];
	private String user1;
	private String user2;
	private String gid;
	boolean gameover;
	private String gameoverMsg;
	private int moveCount;
	private boolean p1gameoverSent;
	private boolean p2gameoverSent;
	
	private final static int maxidle = 50000;
	private int idletime;
	//boolean toggle, 2 player game, any more is a waste of memory
	public boolean p1turn = true;
	//private String[] commandArr;
	
	public Game(String username1, String username2, String gameid){
		//Method to keep track of players
		user1 = username1;
		user2 = username2;
		gid = gameid;
		idletime = 0;
		gameover = false;
		gameoverMsg = "";
		moveCount = 0;
		p1gameoverSent = false;
		p2gameoverSent = false;
	}
	
	public boolean sentBothGameover()
	{
		if (p1gameoverSent && p2gameoverSent)
			return true;
		
		return false;
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
		
		String boardData = "";
		for (int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if ((grid[i][j] != 'X') && (grid[i][j] != 'O'))
				{
					if ((i==2) && (j==2))
						boardData += "_";
					else
						boardData += "_:";
				}
				else if( (i==2) && (j==2))
				{
					boardData += grid[i][j];
				}
				else
				{
					boardData += grid[i][j] + ":";
				}
			}
		}
		if (p1turn && gameover)
		{
			p1gameoverSent = true;
			return ("gamedata:" + gid + ":" + user1 + ":" + user2 + ":" + temp + ":" + gameoverMsg + ":" + boardData);
		}
		else if (!p1turn && gameover)
		{
			p2gameoverSent = true;
			return ("gamedata:" + gid + ":" + user1 + ":" + user2 + ":" + temp + ":" + gameoverMsg + ":" + boardData);
		}
		return ("gamedata:" + gid + ":" + user1 + ":" + user2 + ":" + temp + ":" + boardData);
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
	public String validateMove(String gamemove) 
	{
		if (gameover)
			return gameoverMsg;
		//String temp = gamemove[1];
		String[] tempArr = gamemove.split(",");
		int x = Integer.parseInt(tempArr[0]);
		int y = Integer.parseInt(tempArr[1]);
		
		if ((x < 0 || x > 2) || (y < 0 || y > 2)) 
		{
			return "failed:" + gid + ":invalid move";
		}
		//Check if spot is taken
		else if (grid[x][y] == 'O' || grid[x][y] == 'X')
		{
			return "failed:" + gid + ":invalid move";
		}
		else if (p1turn)
		{
				moveCount++;
				grid[x][y] = 'O';
				checkWin(x,y);
				p1turn = !p1turn;
		}
		else
		{
			moveCount++;
			grid[x][y] = 'X';
			checkWin(x,y);
			p1turn = !p1turn;
		}

		idletime = 0;
		
		if (p1turn && gameover)
		{
			p1gameoverSent = true;
			return gameoverMsg;
		}
		else if (!p1turn && gameover)
		{
			p2gameoverSent = true;
			return gameoverMsg;
		}
		
		return "success:" + gid + ":move complete";
		
	}

	//increases idle time. returns false if idle time is to high
	public boolean checkidle(){
		if (this.sentBothGameover())
			return false;
		if (idletime > 2*maxidle)
			return false;
		if ((idletime > maxidle) && (gameover == false))
		{
			//TODO add game termination function -- SHOULD BE TODONE NOW
			//gameover goes true, current player is the loser
			gameover = true;
			if(p1turn)
			{
				gameoverMsg = "success:win:" + user2 + ":user1 failed to play";
				DBHandler.incrementTotalGamesByUser(user1);
				DBHandler.incrementWinsByUser(user2);
				DBHandler.incrementTotalGamesByUser(user2);
			}
			else
			{
				gameoverMsg = "success:win:" + user1 + ":user2 failed to play";
				DBHandler.incrementTotalGamesByUser(user2);
				DBHandler.incrementWinsByUser(user1);
				DBHandler.incrementTotalGamesByUser(user1);
			}
			return true;
		}
		idletime++;
		return true;
	}
	
	private boolean declareWin(String winner, String loser){
		try {
			//update database values
			DBHandler.incrementTotalGamesByUser(loser);
			DBHandler.incrementWinsByUser(winner);
			DBHandler.incrementTotalGamesByUser(winner);
			
			//updtate loser ranking
			String loseData[] = DBHandler.getDataByUser(loser).split(":");
			if(loseData[0].equals("success"))
				DBHandler.setRankingByUser(loser, Integer.parseInt(loseData[3])-1);
			
			//update winner ranking
			String winData[] = DBHandler.getDataByUser(winner).split(":");
			if(winData[0].equals("success"))
				DBHandler.setRankingByUser(winner, Integer.parseInt(winData[3])+1);
			
			return true;
		} catch (Exception e) {
			System.out.print("declareWin:"+ e);
			return false;
		}
	}

	private boolean checkWin(int x, int y)
	{
		
		//horizontal
		for(int i = 0; i < 3; i++)
		{
			if(grid[x][i] != grid[x][y])
			{
				gameover = false;
				break;
			}
			else if (i==2)
			{
				System.out.println("\nin horizontal win section\n");
//set gameover flag to true, generate winner string to return
				gameover = true;
				if (p1turn)
				{
					this.declareWin(user1, user2);
					gameoverMsg = "success:" + "win:" + user1;
					return true;
				}
				else
				{
					this.declareWin(user2, user1);
					gameoverMsg = "success:" + "win:" + user2;
					return true;
				}
			}
		}
		//vertical
		for(int i = 0; i < 3; i++)
		{
			if(grid[i][y] != grid[x][y])
			{
				gameover = false;
				break;
			}
			else if (i==2)
			{
				System.out.println("firing on vertwin");
				//set gameover flag to true, generate winner string to return
				gameover = true;
				if (p1turn)
				{
					this.declareWin(user1, user2);
					gameoverMsg = "success:" + "win:" + user1;
					return true;
				}
				else
				{
					this.declareWin(user2, user1);
					gameoverMsg = "success:" + "win:" + user2;
					return true;
				}
			}
		}
		
		//diagonal only needs to be checked for 5 of 9 possible piece placements
		//that trigger the board evaluation
		// when x == y or when abs(x-y) == 2
		if ( (x == y) || (Math.abs(x-y) == 2) )
		{
			if ( (grid[0][0] == grid [1][1]) && (grid[1][1] == grid[2][2]) && (grid[1][1] == 'X' || grid[1][1] == 'O'))
			{
				System.out.println("diagwin\n");
				gameover = true;
				
				if (p1turn)
				{
					this.declareWin(user1, user2);
					gameoverMsg = "success:" + "win:" + user1;
					return true;
				}
				else
				{
					this.declareWin(user2, user1);
					gameoverMsg = "success:" + "win:" + user2;
					return true;
				}
			}
			else if ( (grid[2][0] == grid [1][1]) && (grid[1][1] == grid[0][2]) && (grid[1][1] == 'X' || grid[1][1] == 'O'))
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
						System.out.println(grid[i][j]);
					System.out.println("\n");
				}
				System.out.println("diagwin2\n");
				gameover = true;
				if (p1turn)
				{
					this.declareWin(user1, user2);
					gameoverMsg = "success:" + "win:" + user1;
					return true;
				}
				else
				{
					this.declareWin(user2, user1);
					gameoverMsg = "success:" + "win:" + user2;
					return true;
				}
			}
		}
		
		//draw condition
		if ((moveCount == 9) && !gameover)
		{
			DBHandler.incrementTotalGamesByUser(user1);
			DBHandler.incrementTotalGamesByUser(user2);
			gameoverMsg = "success:draw:" + user1 + ":" + user2;
			gameover = true;
			return true;
		}
			
		//catchall line, should never be reached, but tends to make the compiler happy
		return false;
	}
}
