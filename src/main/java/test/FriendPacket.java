package test;

public class FriendPacket {
    String name;
    int userID;

    public FriendPacket(String name, int userID){
        this.name = name;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }
}
