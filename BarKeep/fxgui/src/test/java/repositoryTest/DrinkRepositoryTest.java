package repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import barkeep.Drink;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.DrinkRepository;
import repositories.HttpManager;

public class DrinkRepositoryTest {
  private static Drink testDrink = new Drink("Brus", 45);

  @BeforeAll
  public static void setupDrink() {
    HttpManager.setContext("MrsTest", "besttest");
    testDrink.setId(4);
  }


  @Test
  public void testGetDrink() {
    Drink drink = DrinkRepository.get(4);
    assertNotNull(drink);
    equalDrink(testDrink, drink);
  }

  @Test
  public void testGetAll() {
    List<Drink> allDrinks = DrinkRepository.getAll();
    System.out.println(allDrinks);
    assertFalse(allDrinks.isEmpty());
  }

  private void equalDrink(Drink drink1, Drink drink2) {
    assertEquals(drink1.getId(), drink2.getId());
    assertEquals(drink1.getName(), drink2.getName());
    assertEquals(drink1.getValue(), drink2.getValue());
  }
}
