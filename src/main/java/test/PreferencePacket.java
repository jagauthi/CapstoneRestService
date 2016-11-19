package test;

public class PreferencePacket {
	
	int userID, rating;
	String foodType, foodName;
	int monday, tuesday, wednesday, thursday, friday, saturday, sunday;

	public PreferencePacket(int userID, String foodType, String foodName, int rating)
	{
		this.userID = userID;
		this.foodType = foodType;
		this.foodName = foodName;
		this.rating = rating;
	}

	public PreferencePacket(String foodType, String foodName){
		this.foodName = foodName;
		this.foodType = foodType;
	}
	public int getUserID()
	{
		return userID;
	}

	public String getFoodType() {
		return foodType;
	}

	public String getFoodName() {
		return foodName;
	}
	
	public int getRating() {
		return rating;
	}

	public int getMonday() {
		return monday;
	}

	public int getTuesday() {
		return tuesday;
	}

	public int getWednesday() {
		return wednesday;
	}

	public int getThursday() {
		return thursday;
	}

	public int getFriday() {
		return friday;
	}

	public int getSaturday() {
		return saturday;
	}

	public int getSunday() {
		return sunday;
	}
}
