package barkeep;

import java.time.LocalDateTime;

public class IOweYouDto {
  private int id;
  private UserDto owner;
  private UserDto user;
  private Drink drink;
  private LocalDateTime time;

  public IOweYouDto() {
  }

  /**
   * Constructor for converting a IOweYou to DTO.
   *
   * @param iou the IOweYou to be constructed from.
   */
  public IOweYouDto(IOweYou iou) {
    id = iou.getId();
    owner = new UserDto(iou.getOwner());
    user = new UserDto(iou.getUser());
    drink = iou.getDrink();
    time = iou.getTime();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public UserDto getOwner() {
    return owner;
  }

  public void setOwner(UserDto owner) {
    this.owner = owner;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public Drink getDrink() {
    return drink;
  }

  public void setDrink(Drink drink) {
    this.drink = drink;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }
}
