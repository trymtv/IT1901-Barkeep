package database;

import barkeep.Drink;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DrinkRepositoryTest {
	private static Drink testDrink = new Drink("Brus", 45);

	@BeforeAll
	public static void setupDrink(){
		Database.setDbUrl("jdbc:h2:./src/test/resources/testdb");
		testDrink.setId(4);
		System.out.println("trigger");
	}


	@Test
	public void testGetDrink() throws SQLException, ClassNotFoundException {
		Drink drink = DrinkRepository.get(4);
		equalDrink(testDrink, drink);
	}

	@Test
	public void  testDeleteAndStore() throws SQLException, ClassNotFoundException {
		Drink tempDrink = new Drink("tempDrink", 1337.80085);
		DrinkRepository.store(tempDrink);
		DrinkRepository.delete(tempDrink);
	}

	private void equalDrink(Drink drink1, Drink drink2){
		assertEquals(drink1.getId(), drink2.getId());
		assertEquals(drink1.getName(), drink2.getName());
		assertEquals(drink1.getValue(), drink2.getValue());
	}
}
