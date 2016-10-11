package test;

public class DataPacket {
	
	String username, date;
	int value1, value2, value3;
	
	public DataPacket(String username, String date, int value1, int value2, int value3)
	{
		this.username = username;
		this.date = date;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

	public String getUsername() {
		return username;
	}

	public String getDate() {
		return date;
	}

	public int getValue1() {
		return value1;
	}

	public int getValue2() {
		return value2;
	}

	public int getValue3() {
		return value3;
	}
}
