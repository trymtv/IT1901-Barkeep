package repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


import barkeep.User;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.HttpManager;
import repositories.UserRepository;

public class UserRepositoryTest {
  @BeforeAll
  public static void setupDb() {
    HttpManager.setContext("MrsTest", "besttest");
  }

  @Test
  public void testGetUser() throws SQLException, ClassNotFoundException {
    User testUser = new User(1, "per", "Perperper10", "per@gmail.com");
    User getUsernameUser = UserRepository.get("per");
    User getIdUser = UserRepository.get(1);
    equalUsers(testUser, getIdUser);
    equalUsers(testUser, getUsernameUser);
  }

  private void equalUsers(User user1, User user2) {
    assertEquals(user1.getId(), user2.getId());
    assertEquals(user1.getUsername(), user2.getUsername());
  }
}
