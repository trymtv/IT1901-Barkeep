package barkeep;

public class UserDto {
  private int id;
  private String username;
  private String email;

  /**
   * Constructs the UserDto given a User.
   *
   * @param user the given user.
   */
  public UserDto(User user) {
    if (user != null) {
      this.id = user.getId();
      this.username = user.getUsername();
      this.email = user.getEmail();
    }
  }

  public UserDto() {
  }

  /**
   * Construct a new UserDto using id username and email.
   *
   * @param id the given id.
   * @param username the given username.
   * @param email the given email.
   */
  public UserDto(int id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
