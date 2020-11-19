package repositoryTest;

import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.User;
import repositories.HttpManager;
import repositories.IOweYouRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOweYouRepositoryTest {
    @BeforeAll
    public static void setup(){
        HttpManager.setContext("MrsTest", "besttest");
    }

    @Test
    public void testIOUGetters() throws SQLException, ClassNotFoundException {
        HttpManager.setContext("MrsTest", "besttest");
        User user = new User(119, "MrsTest");
        User user2 = new User(120, "MrTest");
        Drink drink = new Drink("Vann", 20.0);
        drink.setId(1);
        IOweYou iOweYou = new IOweYou(user, user2, drink);
        compareIOUs(iOweYou, IOweYouRepository.getByFriend(user2).get(0));
        compareIOUs(iOweYou, IOweYouRepository.getByOwner(user).get(0));
    }

    @Test
    public void testDeleteAndStore() throws SQLException, ClassNotFoundException {
        HttpManager.setContext("MrsTest", "besttest");
        User user = new User(119, "MrsTest");
        List<IOweYou> iouList = IOweYouRepository.getByOwner(user);
        IOweYouRepository.delete(iouList.get(iouList.size()-1));
        IOweYouRepository.store(iouList.get(0));
        compareIOUs(iouList.get(0), IOweYouRepository.getByOwner(user).get(0));
    }

    private void compareIOUs(IOweYou IOweYou1, IOweYou IOweYou2) {
        assertEquals(IOweYou1.getOwner().getId(), IOweYou2.getOwner().getId());
        assertEquals(IOweYou1.getUser().getId(), IOweYou2.getUser().getId());
        assertEquals(IOweYou1.getDrink().getId(), IOweYou2.getDrink().getId());
    }
}
