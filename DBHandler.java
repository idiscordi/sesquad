import java.sql.*;

public final class DBHandler {
	public static String DBLogin(String username, String password) {
		String dbpassword;
		System.out.println("\nSetting driver string\n");
		String JDBC_Driver = "com.mysql.jdbc.Driver";
		try {
			System.out.println("\nAttempting driver load\n");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("\ndriver should have been loaded\n");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/squaddb", "handler", "handler");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password FROM users WHERE username = " + username);
	
			while (rs.next()) {
				dbpassword = rs.getString("password");
		
				if(dbpassword.equals(password)) {
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
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT email FROM users");
			
			while (rs.next()) {
				dbemail = rs.getString("email");
				
				if(dbemail.equals(email)) {
					conn.close();
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
			
			while (rs.next()) {
				dbusername = rs.getString("username");
				
				if(dbusername.equals(username)) {
					conn.close();
					return "failed:username is Taken";
				}
			}
			
			/*
			This should be 
			String sql = "INSERT INTO users (username, password, email, wins, totalgames, ranking, online)"
			+ "VALUES ('" + " + username + "', '" + dbpassword + "', '" + dbemail + "', '0', '0', '0', '0')";
			*/
			String sql = "INSERT INTO users (username, password, email, wins, totalgames, ranking, online)"
			+ "VALUES ("+username+", "+password+", "+email+", '0', '0', '0', '0')";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e);
		}
		return "success:user account created";
	}	
}