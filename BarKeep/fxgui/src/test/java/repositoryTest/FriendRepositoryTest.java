package repositoryTest;

import barkeep.User;
import repositories.FriendRepository;
import repositories.HttpManager;
import repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FriendRepositoryTest {

    @BeforeAll
    public static void setupDb(){
        HttpManager.setContext("MrsTest", "besttest");
    }

    @Test
    public void testGetFriendList() throws SQLException, ClassNotFoundException {
        List<User> getIdList = FriendRepository.get(1);
        assertNotNull(getIdList);
        List<User> getUsernameList = FriendRepository.get("per");
        assertNotNull(getUsernameList);
    }

    @Test
    public void testDeleteAndInsert() throws SQLException, ClassNotFoundException {
        User user1 = UserRepository.getAllExcept("per").get(0);
        User user2 = UserRepository.getAllExcept("per").get(2);
        FriendRepository.store(user1.getId(), user2.getId());
        FriendRepository.delete(user2.getId());
        List<User> getList = FriendRepository.get(user2.getId());
        assertNotNull(getList);
    }

}
