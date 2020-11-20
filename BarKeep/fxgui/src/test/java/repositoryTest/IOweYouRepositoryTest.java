package repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.User;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.DrinkRepository;
import repositories.HttpManager;
import repositories.IOweYouRepository;
import repositories.UserRepository;

public class IOweYouRepositoryTest {
  @BeforeAll
  public static void setup() {
    HttpManager.setContext("MrsTest", "besttest");
  }

  @Test
  public void testIOUGetters() throws SQLException, ClassNotFoundException {
    HttpManager.setContext("MrsTest", "besttest");
    User user = UserRepository.get("MrsTest");
    User user2 = UserRepository.get("MrTest");
    Drink drink = new Drink("Vann", 20.0);
    drink.setId(1);
    IOweYou iOweYou = new IOweYou(user, user2, drink);
    compareIOUs(iOweYou, IOweYouRepository.getByFriend(user2).get(0));
    compareIOUs(iOweYou, IOweYouRepository.getByOwner(user).get(0));
  }

  @Test
  public void testDeleteAndStore() {
    HttpManager.setContext("MrsTest", "besttest");
    User user = UserRepository.get("MrsTest");
    User user2 = UserRepository.get("MrTest");
    Drink drink = DrinkRepository.getAll().get(0);
    IOweYou iou = new IOweYou();
    iou.setOwner(user);
    iou.setUser(user2);
    iou.setDrink(drink);
    List<IOweYou> oldList = IOweYouRepository.getByOwner(user);
    assertTrue(IOweYouRepository.store(iou));
    List<IOweYou> newList = IOweYouRepository.getByOwner(user);
    assertTrue(IOweYouRepository.delete(newList.get(newList.size() - 1)));
    assertEquals(oldList.toString(), IOweYouRepository.getByOwner(user).toString());
  }

  private void compareIOUs(IOweYou IOweYou1, IOweYou IOweYou2) {
    assertEquals(IOweYou1.getOwner().getId(), IOweYou2.getOwner().getId());
    assertEquals(IOweYou1.getUser().getId(), IOweYou2.getUser().getId());
    assertEquals(IOweYou1.getDrink().getId(), IOweYou2.getDrink().getId());
  }
}
