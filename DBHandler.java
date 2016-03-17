import java.sql.*;

class DBHandler {
	String dbusername;
	String dbpassword;

	public static DBHandler {	
		try {
			Connection conn = DriverManager.getConnection("jbdc:sqlserver://localhost:1433");
			Statment stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password FROM users WHERE username = " + username);
	
			while (rs.next()) {
				dbusername = rs.getString("username");
				dbpassword = rs.getString("password");
		
				if(dbusername.equals(username) && dbpassword.equals(password)) {
					conn.close();
					return "success:user and pass correct";
				}
			}
		} catch (Exception e) {
			System.err.println("Exception Error");
		}
	}	
}
