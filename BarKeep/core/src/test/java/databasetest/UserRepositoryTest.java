package databasetest;

import barkeep.User;
import database.Database;
import database.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
	@BeforeAll
	public static void setupDb(){
		Database.setDbUrl("jdbc:h2:./src/test/resources/testdb");
	}

	@Test
	public void testGetUser() throws SQLException, ClassNotFoundException {
		User testUser = new User(1, "per", "", "");
		User getUsernameUser = UserRepository.get("per");
		User getIdUser = UserRepository.get(1);
		equalUsers(testUser, getIdUser);
		equalUsers(testUser, getUsernameUser);
	}

	@Test
	public void testDeleteAndStoreUser() throws SQLException, ClassNotFoundException {
		User testUser = new User(0, "mrTestDatabaseUser");
		UserRepository.delete(testUser);
		UserRepository.store(testUser);
		User getUser = UserRepository.get("mrTestDatabaseUser");
		assertNotNull(getUser);
	}

	private void equalUsers(User user1, User user2){
		assertEquals(user1.getId(), user2.getId());
		assertEquals(user1.getUsername(), user2.getUsername());
	}
}
