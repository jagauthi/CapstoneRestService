package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHitter {
	
	public DBHitter()
	{
		
	}

	public String getRows(String name)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://uaf134404.ddns.uark.edu:3306/TestDB","root", "Capstone");
			System.out.print("Database is connected !");
			/*
			Statement stmt = conn.createStatement() ;
			String query = "select * from DataTable ;" ;
			ResultSet rs = stmt.executeQuery(query) ;
			*/
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
