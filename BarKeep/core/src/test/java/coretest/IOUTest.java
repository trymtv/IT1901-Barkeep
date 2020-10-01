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
	private IOU iou;
	private LocalDateTime date;
	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

	@BeforeEach
	public void setupIOU(){
		drink = new Drink("Water",  20.0);
		user = new User("Ola");
		date = LocalDateTime.now();
		iou = new IOU(user, drink);
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
