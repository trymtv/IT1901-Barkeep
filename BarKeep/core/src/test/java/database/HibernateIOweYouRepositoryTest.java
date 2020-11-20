package database;

import barkeep.Drink;
import barkeep.IOweYou;
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
@ContextConfiguration(classes = HibernateIOweYouRepository.class)
public class HibernateIOweYouRepositoryTest {
  @Autowired
  private HibernateIOweYouRepository iOweYouRepository;
  @Autowired
  private HibernateDrinkRepository drinkRepository;
  @Autowired
  private HibernateUserRepository userRepository;
  private IOweYou test1;
  private IOweYou test2;
  private User user1;
  private User user2;
  private User user3;
  private Drink drink;


  @Before
  public void setUp() {
    user1 = new User("user1", "pass1234", "mail@mail.com");
    user2 = new User("user2", "pass1234", "mail@mail.com");
    user3 = new User("user3", "pass1234", "mail@mail.com");
    drink = new Drink("test-drink", 10);
    drinkRepository.save(drink);
    userRepository.save(user1);
    userRepository.save(user2);
    userRepository.save(user3);

    test1 = iOweYouRepository.save(new IOweYou(user1, user2, drink));
    test2 = iOweYouRepository.save(new IOweYou(user2, user3, drink));
  }

  @Test
  public void whenSave_thenReturnIOweYou() {
    IOweYou iou = new IOweYou(user1, user3, drink);
    IOweYou savedIou = iOweYouRepository.save(iou);
    Assert.assertEquals(iou, savedIou);
  }

  @Test
  public void whenFindAllByUser_thenReturnIOweYous() {
    List<IOweYou> ious = iOweYouRepository.findAllByUser(user2);
    Assert.assertFalse(ious.contains(test2));
    Assert.assertTrue(ious.contains(test1));
  }

  @Test
  public void whenFindAllByOwner_thenReturnIOweYous() {
    List<IOweYou> ious = iOweYouRepository.findAllByOwner(user2);
    Assert.assertTrue(ious.contains(test2));
    Assert.assertFalse(ious.contains(test1));
  }

  @Test
  public void whenDelete_thenRemoveFromDB() {
    iOweYouRepository.delete(test1);
    IOweYou deletedIou = iOweYouRepository.findById(test1.getId()).orElse(null);
    Assert.assertNotEquals(test1, deletedIou);
    Assert.assertNull(deletedIou);
  }
}
