package databasetest;

import barkeep.Drink;
import database.HibernateDrinkRepository;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@EntityScan("barkeep")
@EnableJpaRepositories("database")
@DataJpaTest
@ContextConfiguration(classes = HibernateDrinkRepository.class)
public class HibernateDrinkRepositoryTest {
    @Autowired
    private HibernateDrinkRepository drinkRepository;
    private Drink test1;
    private Drink test2;

    @Before
    public void setUp() {
        test1 = drinkRepository.save(new Drink("test123", 1234));
        test2 = drinkRepository.save(new Drink("test1234", 1234));
    }

    @Test
    public void whenSave_thenReturnDrink() {
        Drink drink = new Drink("test", 1234);
        Drink newDrink = drinkRepository.save(drink);
        Assert.assertEquals(drink, newDrink);
    }

    @Test
    public void whenFindFirstByName_thenReturnDrink() {
        Drink drink = drinkRepository.findFirstByName("test123");
        Assert.assertEquals(test1, drink);
    }

    @Test
    public void whenfindByNameIgnoreCaseContaining_thenReturnListOfDrinks() {
        List<Drink> actual = new ArrayList<>();
        actual.add(test1);
        actual.add(test2);
        List<Drink> drinks1 = drinkRepository.findByNameIgnoreCaseContaining("test");
        List<Drink> drinks2 = drinkRepository.findByNameIgnoreCaseContaining("Test12");
        Assert.assertEquals(actual, drinks1);
        Assert.assertEquals(actual, drinks2);
    }
}
