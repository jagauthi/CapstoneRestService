package test;

public class FriendRequestPacket {
    int userID;
    int friendID;

    FriendRequestPacket(int userID, int friendID){
        this.userID = userID;
        this.friendID = friendID;
    }

    public int getUserID() {
        return userID;
    }

    public int getFriendID() {
        return friendID;
    }
}
