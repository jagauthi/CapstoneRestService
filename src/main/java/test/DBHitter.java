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
			conn = DriverManager.getConnection("jdbc:mysql://uaf134404.ddns.uark.edu:3306/TestInProduction", properties);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public UserPacket[] getAccounts(String username)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select * from UserAccounts where username = '" + username + "';" ;
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
			
			UserPacket[] packets = new UserPacket[resultSetSize];
			int counter = 0;
			while(rs.next())
			{
				packets[counter] = new UserPacket(rs.getInt("userID"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
				counter++;
			}
			conn.close();
			return packets;
		} 
		catch (Exception e) {
			e.printStackTrace();
			UserPacket[] errorPacketArray = new UserPacket[1];
			errorPacketArray[0] = new UserPacket(0, "error...;)", "1776-07-04", ":O");
			return errorPacketArray;
		}
	}
	
	public int addAccount(String username, String password, String email)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "insert into UserAccounts values(0, '" + username + "', '" + password + "', + '" + email + "');" ;
			int numRowsAffected = stmt.executeUpdate(query) ;
			return numRowsAffected;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public PreferencePacket[] getPreferences(String username)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select * from Preferences p, UserAccounts u where p.userID = u.userID AND u.username = '" + username + "';" ;
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
			
			PreferencePacket[] packets = new PreferencePacket[resultSetSize];
			int counter = 0;
			while(rs.next())
			{
				packets[counter] = new PreferencePacket(rs.getInt("userID"), rs.getString("foodType"), rs.getString("foodName"), rs.getInt("rating"));
				counter++;
			}
			conn.close();
			return packets;
		} 
		catch (Exception e) {
			e.printStackTrace();
			PreferencePacket[] errorPacketArray = new PreferencePacket[1];
			errorPacketArray[0] = new PreferencePacket(0, "error...;)", "1776-07-04", 0);
			return errorPacketArray;
		}
	}
}
