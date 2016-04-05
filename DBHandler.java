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
		
				if(dbpassword.equals(password)) {
					conn.close();
					return "success:user and pass correct\n";
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return "failed:login information does not match\n";
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
					return "failed:email is Taken\n";
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
					return "failed:username is Taken\n";
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
		
		return "success:user account created\n";
	}	
}
