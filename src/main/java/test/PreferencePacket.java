package test;

public class PreferencePacket {
	
	int userID, rating;
	String foodType, foodName;
	
	public PreferencePacket(int userID, String foodType, String foodName, int rating)
	{
		this.userID = userID;
		this.foodType = foodType;
		this.foodName = foodName;
		this.rating = rating;
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

}
