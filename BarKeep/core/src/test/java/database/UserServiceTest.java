package database;

import barkeep.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = UserService.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private HibernateUserRepository userRepository;

    private User user1;
    private User user2;

    @Before
    public void setUp() {
        user1 = new User("TestDrink1",  "d", "h@h.com");
        user2 = new User("TestDrink2",  "d", "h2@h.com");
    }

    @Test
    public void whenList_thenContainsUsers() {
        given(userRepository.findAll()).willReturn(Arrays.asList(user1, user2));
        List<User> users = userService.list();
        Assert.assertTrue(users.contains(user1));
        Assert.assertTrue(users.contains(user2));
    }

    @Test
    public void whenSearchByName_notCaseSensitive_thenReturnListOfUsers() {
        given(userRepository.findByUsernameIgnoreCaseContaining("testuser"))
                .willReturn(Arrays.asList(user1, user2));
        List<User> users = userService.searchByUsername("testuser");
        Assert.assertTrue(users.contains(user1));
        Assert.assertTrue(users.contains(user2));
    }
}
