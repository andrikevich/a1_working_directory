package by.a1.andrikevich.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;





public class TestJdbcConnection {

	
	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/drivetestgroup?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "andrikevich";
		String pwd = "2wsx#EDC";
		try {
			System.out.println("Connecting to DB... "+ jdbcUrl);
			System.out.println("Trying to connect ...");
			Connection connection  = DriverManager.getConnection(jdbcUrl, user, pwd);
			
			System.out.println("Connection sucessful !!!");
			Statement statement = connection.createStatement();
			statement.execute("select * from users");
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		

	}

}
