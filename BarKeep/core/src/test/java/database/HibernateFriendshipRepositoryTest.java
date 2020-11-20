package database;

import barkeep.Friendship;
import barkeep.User;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EntityScan("barkeep")
@EnableJpaRepositories("database")
@DataJpaTest
@ContextConfiguration(classes = HibernateFriendshipRepository.class)
public class HibernateFriendshipRepositoryTest {
  @Autowired
  private HibernateFriendshipRepository friendshipRepository;
  @Autowired
  private HibernateUserRepository userRepository;
  private User user1;
  private User user2;
  private Friendship savedFriendship;

  @Before
  public void setUp() {
    user1 = userRepository.save(new User("test1", "12345678", "lol@l.com"));
    user2 = userRepository.save(new User("test2", "12345678", "lol1@l.com"));
    Friendship friendship = new Friendship();
    friendship.setUser1(user1);
    friendship.setUser2(user2);
    savedFriendship = friendshipRepository.save(friendship);
  }

  @Test
  public void whenSave_thenReturnFriendship() {
    Assert.assertEquals(user1, savedFriendship.getUser1());
    Assert.assertEquals(user2, savedFriendship.getUser2());
    Assert.assertNotNull(savedFriendship.getId());
  }

  @Test
  public void whenGetFriendshipByUser1OrUser2_thenReturnFriendships() {
    List<Friendship> friendships1 =
        friendshipRepository.getFriendshipByUser1OrUser2(user1, user1);
    List<Friendship> friendships2 =
        friendshipRepository.getFriendshipByUser1OrUser2(user2, user2);
    Assert.assertEquals(friendships1, friendships2);
    Assert.assertTrue(friendships1.contains(savedFriendship));
  }

  @Test
  public void whenGetFriendshipBetween_thenReturnFriendship() {
    Friendship friendshipU1U2 = friendshipRepository.getFriendshipBetween(user1, user2);
    Friendship friendshipU2U1 = friendshipRepository.getFriendshipBetween(user2, user1);
    Assert.assertEquals(friendshipU1U2, friendshipU2U1);
    Assert.assertTrue(friendshipU1U2.getUser1() == user1 || friendshipU1U2.getUser1() == user2);
    Assert.assertTrue(friendshipU1U2.getUser2() == user1 || friendshipU1U2.getUser2() == user2);
  }
}
