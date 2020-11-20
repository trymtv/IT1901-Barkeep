package core;

import static org.junit.jupiter.api.Assertions.assertEquals;


import barkeep.Drink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkTest {
  private Drink drink1;

  @BeforeEach
  public void setupDrink() {
    drink1 = new Drink("Vann", 0);
  }

  @Test
  public void testDrinkChange() {
    drink1.setName("IkkeVann");
    drink1.setValue(20);
    testDrinkGet("IkkeVann", 20.0);

  }

  private void testDrinkGet(String name, Double value) {
    assertEquals(name, drink1.getName());
    assertEquals(name, drink1.toString());
    assertEquals(value, drink1.getValue());
  }


}
