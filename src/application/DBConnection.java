package application;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection connector() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ai","root","");
			return conn;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}



}
