package core;

import static org.junit.jupiter.api.Assertions.assertEquals;


import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.IOweYouDto;
import barkeep.User;
import barkeep.UserDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IOweYouDtoTest {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  private Drink drink;
  private User user;
  private UserDto userDTO;
  private User owner;
  private IOweYou iou;
  private IOweYouDto iouDTO;
  private LocalDateTime date;
  private LocalDateTime newDate;

  @BeforeEach
  public void setupIOweYou() {
    drink = new Drink("Water", 20.0);
    user = new User(1, "per");
    owner = new User(2, "perolav");
    date = LocalDateTime.now();
    iou = new IOweYou(owner, user, drink);
    iouDTO = new IOweYouDto(iou);

  }

  @Test
  public void testId() {
    iouDTO.setId(1);
    checkId();
  }

  @Test
  public void testOwner() {
    userDTO = new UserDto();
    userDTO.setUsername("petter");
    iouDTO.setOwner(userDTO);
    checkOwner();
  }

  @Test
  public void testUser() {
    userDTO = new UserDto();
    userDTO.setUsername("petter");
    iouDTO.setUser(userDTO);
    checkUser();
  }

  @Test
  public void testDrink() {
    drink = new Drink("Vodka", 30.0);
    iouDTO.setDrink(drink);
    checkDrink();
  }

  @Test
  public void testTime() {
    newDate = date.plusHours(1);
    iouDTO.setTime(newDate);
    checkTime();
  }

  private void checkId() {
    assertEquals(iouDTO.getId(), 1);
  }

  private void checkOwner() {
    assertEquals(iouDTO.getOwner(), userDTO);
  }

  private void checkUser() {
    assertEquals(iouDTO.getUser(), userDTO);
  }

  private void checkTime() {
    assertEquals(iouDTO.getTime(), newDate);
  }

  private void checkDrink() {
    assertEquals(iouDTO.getDrink(), drink);
  }


}
