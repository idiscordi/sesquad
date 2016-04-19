import java.sql.*;

public final class DBHandler {
	public static String DBLogin(String username, String password) {
		String dbpassword;
		//System.out.println("\nSetting driver string\n");
		String JDBC_Driver = "com.mysql.jdbc.Driver";
		try {
			//System.out.println("\nAttempting driver load\n");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("\ndriver should have been loaded\n");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password FROM users WHERE username = '" + username +"'");
	
			while (rs.next()) {
				dbpassword = rs.getString("password");
		
				if(dbpassword.equals(password)) 
				{
					stmt.executeUpdate("UPDATE users SET online ='1' WHERE username='" + username +"'");
					conn.close();
					return "success:user and pass correct";
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return "failed:login information does not match";
	}

    public static String DBNewAcc(String username, String password, String email) {
		String dbusername;
		String dbpassword;
		String dbemail;
		String JDBC_Driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			//System.out.println("making sql statement\n");
			Statement stmt = conn.createStatement();
			//System.out.println("executing, getting results\n");
			ResultSet rs = stmt.executeQuery("SELECT email FROM users");
			
			//System.out.println("while rs.next()\n");
			while (rs.next()) {
				//System.out.println("getstring email\n");
				if(rs.getString("email") == null)
				{
					//System.out.println("got nullemail set to \"\"\n");
					dbemail = "";
				}
				else 
				{
					dbemail = rs.getString("email");
					//System.out.println("got email as " + dbemail + "\n");
				}

				
				if(dbemail == email) {
					//System.out.println("close conn\n");
					conn.close();
					//System.out.println("return fail on email\n");
					return "failed:email is Taken";
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM users");
			while (rs.next()) 
			{
				if(rs.getString("username") == null)
					dbusername = "";
				else
					dbusername = rs.getString("username");
				
				if(dbusername.equals(username))
				{
					conn.close();
					return "failed:username is Taken";
				}
			}
			
			/*
			This should be 
			String sql = "INSERT INTO users (username, password, email, wins, totalgames, ranking, online)"
			+ "VALUES ('" + " + username + "', '" + dbpassword + "', '" + dbemail + "', 0, 0, 0, 0)";
			*/
			String sql = "INSERT INTO users (username, password, email, wins, totalgames, ranking, online)"
			+ "VALUES ('"+username+"', '"+password+"', '"+email+"', 0, 0, 0, 0)";
			stmt.executeUpdate(sql);
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return "success:user account created";
	}	
    
    public static String getDataByUser(String username)
    {
		try {
			//System.out.println("\nAttempting driver load\n");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("\ndriver should have been loaded\n");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username +"'");
			conn.close();
			String toReturn = "success:";
			
			while (rs.next()) 
			{
				 toReturn += rs.getString("wins") + ":";
				 toReturn += rs.getString("totalgames") + ":";
				 toReturn += rs.getString("ranking");
			}
			if (toReturn.equals("success:"))
				return "failed:username not found";
			else return toReturn;
			
		} catch (Exception e) 
		{
			System.err.println(e);
			return "failed: something broke in handler";
		}
    }
    
    public static boolean incrementWinsByUser(String username)
    {
    	try 
    	{
			//System.out.println("\nAttempting driver load\n");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("\ndriver should have been loaded\n");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT wins FROM users WHERE username = '" + username +"'");
			
			while (rs.next()) 
			{
				 int temp =  rs.getInt("wins");
				 temp++;
				 stmt.executeUpdate("UPDATE users SET wins ='" + temp + "' WHERE username='" + username +"'");
				 conn.close();
				 return true;
			}
			
			conn.close();
			return false;
			
    	} catch (Exception e) { System.out.println(e); return false; }
    	//return false;
    }
    
    public static boolean incrementTotalGamesByUser(String username)
    {
    	try 
    	{
			//System.out.println("\nAttempting driver load\n");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("\ndriver should have been loaded\n");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT totalgames FROM users WHERE username = '" + username +"'");
			
			while (rs.next()) 
			{
				 int temp =  rs.getInt("totalgames");
				 temp++;
				 stmt.executeUpdate("UPDATE users SET totalgames ='" + temp + "' WHERE username='" + username +"'");
				 conn.close();
				 return true;
			}
			
			conn.close();
			return false;
			
    	} catch (Exception e) { System.out.println(e); return false; }
    	//return false;
    }
    
    public static boolean setRankingByUser(String username, int newRank)
    {
    	try 
    	{
			//System.out.println("\nAttempting driver load\n");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("\ndriver should have been loaded\n");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			//still querying their old ranking to make sure the account exists
			//if no username match is found, the update will not fire and the conn will close and 
			//return false
			ResultSet rs = stmt.executeQuery("SELECT ranking FROM users WHERE username = '" + username +"'");
			
			while (rs.next()) 
			{
				 stmt.executeUpdate("UPDATE users SET ranking ='" + newRank + "' WHERE username='" + username +"'");
				 conn.close();
				 return true;
			}
			conn.close();
			return false;
			
    	} catch (Exception e) { System.out.println(e); return false; }
    	//return false;
    }
    
    public static boolean toggleOnline(String username)
    {
    	try 
    	{
			//System.out.println("\nAttempting driver load\n");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("\ndriver should have been loaded\n");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			//still querying their old ranking to make sure the account exists
			//if no username match is found, the update will not fire and the conn will close and 
			//return false
			ResultSet rs = stmt.executeQuery("SELECT online FROM users WHERE username = '" + username +"'");
			
			while (rs.next()) 
			{
				 int toggle = rs.getInt("online");
				 //swap values
				 if(toggle == 0)
				 {
					 toggle = 1;
				 }
				 else
					 toggle = 0;
				 
				 stmt.executeUpdate("UPDATE users SET online ='" + toggle + "' WHERE username='" + username +"'");
				 conn.close();
				 return true;
			}
			conn.close();
			return false;
			
    	} catch (Exception e) { System.out.println(e); return false; }
    	//return false;
    }
}
