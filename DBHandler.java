import java.sql.*;

class DBHandler {
	public static DBHandlerLogin(String username, String password) {
		String dbusername;
		String dbpassword;
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

    public static DBHandlerNewAcc(String username, String password, String email) {
		String dbusername;
		String dbpassword;
		String dbemail;
		try {
			Connection conn = DriverManager.getConnection("jbdc:sqlserver://localhost:1433");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT email FROM users WHERE = " + username);
			
			while (rs.next()) {
				dbusername = rs.getString("username");
				dbemail = rs.getString("email");
				
				if(dbusername.equals(username) || dbemail.equals(email)) {
					conn.close();
					return "failed:Username or Email taken";
				}
			}
		} catch (Exception e) {
			System.err.println("Exception Error");
		}
	}	
}


//INSERT FUNCTION
// create a Statement from the connection
Statement statement = conn.createStatement();

// insert the data
statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
statement.executeUpdate("INSERT INTO Customers " + "VALUES (1002, 'McBeal', 'Ms.', 'Boston', 2004)");
statement.executeUpdate("INSERT INTO Customers " + "VALUES (1003, 'Flinstone', 'Mr.', 'Bedrock', 2003)");
statement.executeUpdate("INSERT INTO Customers " + "VALUES (1004, 'Cramden', 'Mr.', 'New York', 2001)");
