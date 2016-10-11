package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBHitter {
	
	Connection conn;
	
	public DBHitter()
	{
		conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			properties.setProperty("user", "dao");
			properties.setProperty("password", "password");
			properties.setProperty("useSSL", "false");
			conn = DriverManager.getConnection("jdbc:mysql://uaf134404.ddns.uark.edu:3306/TestDB", properties);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public DataPacket[] getRows(String username)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select * from DataTable where username = '" + username + "';" ;
			ResultSet rs = stmt.executeQuery(query) ;
			
			int resultSetSize = 0;
			try {
			    rs.last();
			    resultSetSize = rs.getRow();
			    rs.beforeFirst();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			DataPacket[] packets = new DataPacket[resultSetSize];
			int counter = 0;
			while(rs.next())
			{
				packets[counter] = new DataPacket(rs.getString("username"), rs.getDate("date").toString(), 
											rs.getInt("value1"), rs.getInt("value2"), rs.getInt("value3"));
				counter++;
			}
			conn.close();
			return packets;
		} 
		catch (Exception e) {
			e.printStackTrace();
			DataPacket[] errorPacketArray = new DataPacket[1];
			errorPacketArray[0] = new DataPacket("error...;)", "1776-07-04", 6, 6, 6);
			return errorPacketArray;
		}
	}
}
