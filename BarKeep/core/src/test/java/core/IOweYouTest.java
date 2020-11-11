package core;

import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOweYouTest {
	private Drink drink;
	private User user;
	private User owner;
	private IOweYou iou;
	private LocalDateTime date;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@BeforeEach
	public void setupIOweYou(){
		drink = new Drink("Water",  20.0);
		user = new User(1, "per");
		owner = new User(2, "perolav");
		date = LocalDateTime.now();
		iou = new IOweYou(owner, user, drink);
	}

	@Test
	public void testConstructor() {
		assertEquals(iou.getDrink(), drink);
		assertEquals(iou.getUser(), user);
		assertEquals(iou.getTime().format(formatter), date.format(formatter));
	}

	@Test
	public void testToString(){
		String expected = "IOweYou{" +
				"owner=" + owner +
				", user=" + user +
				", drink=" + drink +
				", time=" + date.format(formatter) +
				'}';
		assertEquals(expected, iou.toString());
	}

}
