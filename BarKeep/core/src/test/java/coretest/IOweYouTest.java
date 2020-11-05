package coretest;

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
	private IOweYou IOweYou;
	private LocalDateTime date;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@BeforeEach
	public void setupIOU(){
		drink = new Drink("Water",  20.0);
		user = new User(1, "per", "", "", "");
		owner = new User(2, "perolav", "", "", "");
		date = LocalDateTime.now();
		IOweYou = new IOweYou(owner, user, drink);
	}

	@Test
	public void testConstructor() {
		assertEquals(IOweYou.getDrink(), drink);
		assertEquals(IOweYou.getUser(), user);
		assertEquals(IOweYou.getTime().format(formatter), date.format(formatter));
	}

	@Test
	public void testToString(){
		String expected = "IOweYou{" + "user=" + user.toString() +
				", drink=" + drink.toString() +
				", time=" + date.format(formatter) +
				'}';
		assertEquals(IOweYou.toString(), expected);
	}

}
