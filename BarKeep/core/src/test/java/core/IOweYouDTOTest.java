package core;

import barkeep.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOweYouDTOTest {
	private Drink drink;
	private User user;
	private UserDTO userDTO;
	private User owner;
	private IOweYou iou;
	private IOweYouDTO iouDTO;
	private LocalDateTime date;
	private LocalDateTime newDate;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@BeforeEach
	public void setupIOweYou(){
		drink = new Drink("Water",  20.0);
		user = new User(1, "per");
		owner = new User(2, "perolav");
		date = LocalDateTime.now();
		iou = new IOweYou(owner, user, drink);
		iouDTO = new IOweYouDTO(iou);

	}

	@Test
	public void testId() {
		iouDTO.setId(1);
		checkId();
	}

	@Test
	public void testOwner() {
		userDTO = new UserDTO();
		userDTO.setUsername("petter");
		iouDTO.setOwner(userDTO);
		checkOwner();
	}

	@Test
	public void testUser() {
		userDTO = new UserDTO();
		userDTO.setUsername("petter");
		iouDTO.setUser(userDTO);
		checkUser();
	}

	@Test
	public void testDrink() {
		drink = new Drink("Vodka", 30.0);
		iouDTO.setDrink(drink);
		checkDrink();
	}

	@Test
	public void testTime() {
		newDate = date.plusHours(1);
		iouDTO.setTime(newDate);
		checkTime();
	}

	private void checkId() {
		assertEquals(iouDTO.getId(), 1);
	}
	private void checkOwner() {
		assertEquals(iouDTO.getOwner(), userDTO);
	}
	private void checkUser() {
		assertEquals(iouDTO.getUser(), userDTO);
	}

	private void checkTime() {
		assertEquals(iouDTO.getTime(), newDate);
	}
	private void checkDrink() {
		assertEquals(iouDTO.getDrink(), drink);
	}



}
