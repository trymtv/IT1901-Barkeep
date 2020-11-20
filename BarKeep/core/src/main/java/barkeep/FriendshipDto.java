package barkeep;

public class FriendshipDto {
  private int id;
  private UserDto user1;
  private UserDto user2;

  public FriendshipDto() {
  }

  /**
   * Constructor for a friendshipDto given a frienship.
   *
   * @param friendship the frienship to be converted.
   */
  public FriendshipDto(Friendship friendship) {
    System.out.println(friendship);
    this.id = friendship.getId();
    this.user1 = new UserDto(friendship.getUser1());
    this.user2 = new UserDto(friendship.getUser2());
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public UserDto getUser1() {
    return user1;
  }

  public void setUser1(UserDto user1) {
    this.user1 = user1;
  }

  public UserDto getUser2() {
    return user2;
  }

  public void setUser2(UserDto user2) {
    this.user2 = user2;
  }
}
