import java.sql.*;

class DBHandler {
	String dbusername;
	String dbpassword;
	boolean log = false;
	
	try {
		
		Connection conn = DriverManager.getConnection("jbdc:sqlserver://localhost:1433");
		Statment stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT password FROM users WHERE username = " + username);
	
		while (rs.next()) {
			dbusername = rs.getString("username");
			dbpassword = rs.getString("password");
		
			if(dbusername.equals(username) && dbpassword.equals(password)) {
				return "success:user and pass correct";
				log = true;
			}
		}
		conn.close();
	} catch (Exception e) {
		System.err.println("Exception Error");
	}
	return log;
}