package coretest;

import barkeep.Drink;
import barkeep.IOU;
import barkeep.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOUTest {
	private Drink drink;
	private User user;
	private User owner;
	private IOU iou;
	private LocalDateTime date;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@BeforeEach
	public void setupIOU(){
		drink = new Drink("Water",  20.0);
		user = new User(1, "per", "", "", "");
		owner = new User(2, "perolav", "", "", "");
		date = LocalDateTime.now();
		iou = new IOU(owner, user, drink);
	}

	@Test
	public void testConstructor() {
		assertEquals(iou.getDrink(), drink);
		assertEquals(iou.getUser(), user);
		assertEquals(iou.getTime().format(formatter), date.format(formatter));
	}

	@Test
	public void testToString(){
		String expected = "IOU{" + "user=" + user.toString() +
				", drink=" + drink.toString() +
				", time=" + date.format(formatter) +
				'}';
		assertEquals(iou.toString(), expected);
	}

}
