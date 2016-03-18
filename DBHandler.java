import java.sql.*;

class DBHandler {
	public static String DBLogin(String username, String password) {
		String dbpassword;
		try {
			Connection conn = DriverManager.getConnection("jbdc:sqlserver://localhost:1433");
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
		try {
			Connection conn = DriverManager.getConnection("jbdc:sqlserver://localhost:1433");
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
			Connection conn = DriverManager.getConnection("jbdc:sqlserver://localhost:1433");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM users");
			
			while (rs.next()) {
				dbusername = rs.getString("username");
				
				if(dbusername.equals(username)) {
					conn.close();
					return "failed:username is Taken";
				}
			}
			String sql = "INSERT INTO users (username, password, email, wins, totalgames, ranking, online)"
			+ "VALUES ('dbusername', 'dbpassword', 'dbemail', '0', '0', '0', '0')";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e);
		}
		return "success:user account created";
	}	
}