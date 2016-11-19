package test;

public class FoodPacket {

	String foodType, foodName;
	
	public FoodPacket(String foodType, String foodName)
	{
		this.foodType = foodType;
		this.foodName = foodName;
	}

	public String getFoodType() {
		return foodType;
	}

	public String getFoodName() {
		return foodName;
	}
}
