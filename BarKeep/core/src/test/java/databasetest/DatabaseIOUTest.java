package databasetest;

import barkeep.Drink;
import barkeep.IOU;
import barkeep.User;
import database.Database;
import database.DatabaseIOU;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseIOUTest {
	@BeforeAll
	public static void setupIOU(){
		Database.setDbUrl("jdbc:h2:./src/test/resources/testdb");
	}

	@Test
	public void testIOUGetters() throws SQLException, ClassNotFoundException {
		User user = new User(1, "per", "", "", "");
		User user2 = new User(2, "perolav", "", "", "");
		Drink drink = new Drink("Vann", 20.0);
		drink.setId(1);
		IOU iou = new IOU(user, user2, drink);
		compareIOUs(iou, DatabaseIOU.getByOwner(user).get(0));
		compareIOUs(iou, DatabaseIOU.getByFriend(user2).get(0));
	}

	@Test
	public void testDeleteAndStore() throws SQLException, ClassNotFoundException {
		User user = new User(2, "per", "", "", "");
		List<IOU> iouList = DatabaseIOU.getByOwner(user);
		DatabaseIOU.delete(iouList.get(0));
		DatabaseIOU.store(iouList.get(0));
		compareIOUs(iouList.get(0), DatabaseIOU.getByOwner(user).get(0));
	}

	private void compareIOUs(IOU iou1, IOU iou2){
		assertEquals(iou1.getOwner().getId(), iou2.getOwner().getId());
		assertEquals(iou1.getUser().getId(), iou2.getUser().getId());
		assertEquals(iou1.getDrink().getId(), iou2.getDrink().getId());
	}
}
