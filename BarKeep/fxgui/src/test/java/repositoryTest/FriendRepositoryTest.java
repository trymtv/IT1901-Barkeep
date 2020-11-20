package repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import barkeep.User;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.FriendRepository;
import repositories.HttpManager;
import repositories.UserRepository;

public class FriendRepositoryTest {

  @BeforeAll
  public static void setupDb() {
    HttpManager.setContext("MrsTest", "besttest");
  }

  @Test
  public void testGetFriendList() {
    User loginUser = UserRepository.get("MrsTest");
    User user1 = UserRepository.getAllExcept("MrsTest").get(0);
    User user2 = UserRepository.getAllExcept("MrsTest").get(1);
    FriendRepository.store(user1.getId(), user2.getId());
    assertNotNull(loginUser);
    List<User> getUsernameList = FriendRepository.get(loginUser.getId());
    assertNotNull(getUsernameList);
  }

  @Test
  public void testDeleteAndInsert() {
    User user1 = UserRepository.getAllExcept("per").get(0);
    User user2 = UserRepository.getAllExcept("per").get(2);
    FriendRepository.store(user1.getId(), user2.getId());
    FriendRepository.delete(user2.getId());
    List<User> getList = FriendRepository.get(user2.getId());
    assertNull(getList);
  }

}
