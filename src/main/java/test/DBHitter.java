package test;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.springframework.stereotype.Repository;

public class DBHitter {
	
	Connection conn;

	List<String> days = new ArrayList<>();
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
		days.add("monday");
		days.add("tuesday");
		days.add("wednesday");
		days.add("thursday");
		days.add("friday");
		days.add("saturday");
		days.add("sunday");
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
	
	public UserPacket[] getAccountsByEmail(String email)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select * from UserAccounts where email = '" + email + "';" ;
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
	
	public PreferencePacket[] getPreferences(int userID)
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select * from Preferences where userID = " + userID + ";" ;
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
	
	public FoodPacket[] getAllFood()
	{
		try {			
			Statement stmt = conn.createStatement() ;
			String query = "select f1.typeName, f2.foodName from FoodTypes f1, Foods f2 where f1.typeID = f2.foodType;";
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
			
			FoodPacket[] packets = new FoodPacket[resultSetSize];
			int counter = 0;
			while(rs.next())
			{
				packets[counter] = new FoodPacket(rs.getString("typeName"), rs.getString("foodName"));
				counter++;
			}
			conn.close();
			return packets;
		} 
		catch (Exception e) {
			e.printStackTrace();
			FoodPacket[] errorPacketArray = new FoodPacket[1];
			errorPacketArray[0] = new FoodPacket("error...;)", "1776-07-04");
			return errorPacketArray;
		}
	}
	
	public PreferencePacket getSuggestion(int userID) throws SQLException{
		Statement stmt = conn.createStatement();
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		DayOfWeek day = zonedDateTime.getDayOfWeek();
		String dayString = days.get(day.getValue()-1);

		String query = "select * from Preferences where userID=" + Integer.toString(userID);
		List<Double> totals = new ArrayList<>();
		List<PreferencePacket> prefs = new ArrayList<>();
		double sum = 0;
		ResultSet rs = stmt.executeQuery(query);
		rs.beforeFirst();
		while(rs.next()){
			double val = (.6 * (rs.getInt("rating"))) + (.4 * (rs.getInt(dayString)));
			sum += val;
			totals.add(sum);
			prefs.add(new PreferencePacket(rs.getString("foodType"), rs.getString("foodName")));
		}
		Random rand = new Random();
		double choice = rand.nextDouble() * sum;
		for(int i = 0; i < totals.size(); i++) {
			if(choice < totals.get(i)){
				return prefs.get(i);
			}
		}
		return new PreferencePacket("test", "" + choice);
	}
	
	public int addPreference(int userID, String foodType, String foodName, int rating)
	{
		try {	
			ZonedDateTime zonedDateTime = ZonedDateTime.now();
			DayOfWeek day = zonedDateTime.getDayOfWeek();	
			Statement stmt = conn.createStatement() ;
			String query = "select userID, foodType, foodName, " + days.get(day.getValue()-1) + " from Preferences where userID = " + userID + " and foodType = '" + foodType + "' and foodName = '" + foodName + "';";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			//rs.beforeFirst();
			if(rs.next()) {
				int dayCount = Integer.parseInt(rs.getString(days.get(day.getValue()-1))) + 1;
				query = "update Preferences set rating=" + rating + ", " + days.get(day.getValue()-1) + "=" + dayCount + " where userID = " + userID + " and foodType = '" + foodType + "' and foodName = '" + foodName + "';";
				System.out.println(query);
				return stmt.executeUpdate(query);
			}
			else {
				query = "insert into Preferences (userID, foodType, foodName, rating, " + days.get(day.getValue()-1) + ") values (" + userID + ", '" + foodType + "', '" + foodName + "', " + rating + ", 1);" ;
				System.out.println(query);
				int numRowsAffected = stmt.executeUpdate(query) ;
				return numRowsAffected;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public PreferencePacket getNewSuggestion() throws SQLException{
		Statement stmt = conn.createStatement();
		String query = "select * from Preferences";
		ResultSet rs = stmt.executeQuery(query);
		rs.beforeFirst();
		List<PreferencePacket> prefs = new ArrayList<>();
		rs.beforeFirst();
		while(rs.next()){
			prefs.add(new PreferencePacket(rs.getString("foodType"), rs.getString("foodName")));
		}
		Random rand = new Random();
		int choice = rand.nextInt(prefs.size());
		return prefs.get(choice);
	}
	
	public FoodPacket[] getFoodType(String foodName) throws SQLException{
		Statement stmt = conn.createStatement();
		String query = "select f1.typeName, f2.foodName from FoodTypes f1, Foods f2 where f1.typeID = f2.foodType and f2.foodName = '" + foodName + "';";
		ResultSet rs = stmt.executeQuery(query);
		rs.beforeFirst();
		FoodPacket[] result = new FoodPacket[1];
		if(rs.next())
		{
			FoodPacket fp = new FoodPacket(rs.getString("typeName"), rs.getString("foodName"));
			result[0] = fp;
		}
		else
		{
			FoodPacket fp = new FoodPacket("No","Results");
			result[0] = fp;
		}
		
		return result;
	}

	public FriendPacket[] searchUsersByName(String username) throws SQLException{
		String query = "select * from UserAccounts where username = '" + username + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		rs.last();
		int size = rs.getRow();
		rs.beforeFirst();
		int counter = 0;
		FriendPacket[] packets = new FriendPacket[size];
		while(rs.next()){
			packets[counter] = new FriendPacket(rs.getString("username"), rs.getInt("userID"));
			counter++;
		}
		conn.close();
		return packets;
	}

	public int addFriend(int fromID, int toID) throws SQLException{

		String query = "select * from FriendRequests where fromID = " + Integer.toString(fromID) + " and toID=" + Integer.toString(toID);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int numResults = 0;
		rs.beforeFirst();
		if(!rs.next()){
			query = "insert into FriendRequests (fromID, toID) values " + "(" + Integer.toString(fromID) + ", " + Integer.toString(toID) + " )";
			stmt = conn.createStatement();
			numResults = stmt.executeUpdate(query);
		}



		
		return numResults;
	}

//	public int acceptRequest(int userID, int friendID) throws SQLException{
//
//		String query = "select * from Friends where userID = " + Integer.toString(userID) + " and friendID=" + Integer.toString(friendID);
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		int numResults = 0;
//		rs.beforeFirst();
//		if(!rs.next()) {
//			query = "insert ignore into Friends (userID, friendID) values (" + Integer.toString(userID) + ", " + Integer.toString(friendID) + ")";
//			stmt = conn.createStatement();
//			numResults = stmt.executeUpdate(query);
//		}
//		return numResults;
//	}

	public FriendRequestPacket[] getRequests(int fromID) throws SQLException{
//		String query = "select * from FriendRequests where userID=" + Integer.toString(userID);
		String query = "select * from UserAccounts where userID in (select toID from FriendRequests where fromID =" + Integer.toString(fromID) + ")";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		rs.last();
		int size = rs.getRow();
		FriendRequestPacket[] packets = new FriendRequestPacket[size];
		rs.beforeFirst();
		int counter = 0;
		while(rs.next()){
			packets[counter] = new FriendRequestPacket(fromID, rs.getInt("userID"), rs.getString("username"));
			counter++;
		}
		conn.close();
		return packets;
	}

}
