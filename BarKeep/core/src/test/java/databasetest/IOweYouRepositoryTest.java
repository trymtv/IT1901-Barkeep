package databasetest;

import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.User;
import database.Database;
import database.IOweYouRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IOweYouRepositoryTest {
	@BeforeAll
	public static void setupIOU(){
		Database.setDbUrl("jdbc:h2:./src/test/resources/testdb");
	}

	@Test
	public void testIOUGetters() throws SQLException, ClassNotFoundException {
		User user = new User(1, "per");
		User user2 = new User(2, "perolav");
		Drink drink = new Drink("Vann", 20.0);
		drink.setId(1);
		IOweYou IOweYou = new IOweYou(user, user2, drink);
		compareIOUs(IOweYou, IOweYouRepository.getByOwner(user).get(0));
		compareIOUs(IOweYou, IOweYouRepository.getByFriend(user2).get(0));
	}

	@Test
	public void testDeleteAndStore() throws SQLException, ClassNotFoundException {
		User user = new User(2, "per");
		List<IOweYou> IOweYouList = IOweYouRepository.getByOwner(user);
		IOweYouRepository.delete(IOweYouList.get(0));
		IOweYouRepository.store(IOweYouList.get(0));
		compareIOUs(IOweYouList.get(0), IOweYouRepository.getByOwner(user).get(0));
	}

	private void compareIOUs(IOweYou IOweYou1, IOweYou IOweYou2){
		assertEquals(IOweYou1.getOwner().getId(), IOweYou2.getOwner().getId());
		assertEquals(IOweYou1.getUser().getId(), IOweYou2.getUser().getId());
		assertEquals(IOweYou1.getDrink().getId(), IOweYou2.getDrink().getId());
	}
}
