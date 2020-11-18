package database;

import barkeep.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EntityScan("barkeep")
@EnableJpaRepositories("database")
@DataJpaTest
@ContextConfiguration(classes = HibernateUserRepository.class)
public class HibernateUserRepositoryTest {
    @MockBean
    public PasswordEncoder passwordEncoder;
    @Autowired
    private HibernateUserRepository userRepository;
    private User test1;
    private User test2;

    @Before
    public void setUp() {
        test1 = userRepository.save(new User("test1", "12345678", "lol@l.com"));
        test2 = userRepository.save(new User("test2", "12345678", "lol1@l.com"));
    }

    @Test
    public void whenSave_thenReturnUser() {
        User newUser = userRepository.save(test1);
        Assert.assertEquals(test1, newUser);
    }

    @Test
    public void whenfindByNameIgnoreCaseContaining_thenReturnListOfUsers() {
        List<User> actual = new ArrayList<>();
        actual.add(test1);
        actual.add(test2);
        List<User> users1 = userRepository.findByUsernameIgnoreCaseContaining("te");
        List<User> users2 = userRepository.findByUsernameIgnoreCaseContaining("TeSt");
        Assert.assertEquals(actual, users1);
        Assert.assertEquals(actual, users2);
    }
}
