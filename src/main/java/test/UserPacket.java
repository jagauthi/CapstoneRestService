package test;

public class UserPacket {
	
	int userID;
	String username, password, email;
	
	public UserPacket(int userID, String username, String password, String email)
	{
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public int getUserID()
	{
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
}
