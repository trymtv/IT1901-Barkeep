package barkeep;

public class FriendshipDTO {
    private int id;
    private UserDTO user1;
    private UserDTO user2;

    public FriendshipDTO() {
    }

    public FriendshipDTO(Friendship friendship){
        this.id = friendship.getId();
        this.user1 = new UserDTO(friendship.getUser1());
        this.user2 = new UserDTO(friendship.getUser2());
    }

    public FriendshipDTO(int id, UserDTO user1, UserDTO user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUser1() {
        return user1;
    }

    public void setUser1(UserDTO user1) {
        this.user1 = user1;
    }

    public UserDTO getUser2() {
        return user2;
    }

    public void setUser2(UserDTO user2) {
        this.user2 = user2;
    }
}
