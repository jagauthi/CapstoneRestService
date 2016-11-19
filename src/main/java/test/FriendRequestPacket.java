package test;

public class FriendRequestPacket {
    int userID;
    int friendID;
    String friendName;

    FriendRequestPacket(int userID, int friendID, String friendName){
        this.userID = userID;
        this.friendID = friendID;
        this.friendName = friendName;
    }

    public int getUserID() {
        return userID;
    }

    public int getFriendID() {
        return friendID;
    }

    public String getFriendName() {
        return friendName;
    }

}
