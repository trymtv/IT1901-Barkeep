package databasetest;

import api.BarkeepApp;
import barkeep.Drink;
import database.DrinkService;
import database.HibernateDrinkRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = BarkeepApp.class)
public class DrinkServiceTest {
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private HibernateDrinkRepository drinkRepository;
    private Drink drink1;
    private Drink drink2;

    @Before
    public void setUp(){
        drink1 = drinkRepository.save(new Drink("TestDrink1",12));
        drink2 = drinkRepository.save(new Drink("TestDrink2",34));
    }
    @Test
    public void whenList_thenContainsDrinks(){
        List<Drink> drinks = drinkService.list();
        Assert.assertTrue(drinks.contains(drink1));
        Assert.assertTrue(drinks.contains(drink2));
    }
    @Test
    public void whenGet_thenReturnDrink(){
        Drink newDrink = drinkService.get(drink1.getId());
        Assert.assertEquals(drink1, newDrink);
    }
    @Test
    public void whenSearchByName_notCaseSensitive_thenReturnListOfDrinks(){
        List<Drink> drinks = drinkService.searchByName("testdrink");
        Assert.assertTrue(drinks.contains(drink1));
        Assert.assertTrue(drinks.contains(drink2));
    }
    @Test
    public void whenDrinkUpdateSave_thenReturnUpdatedDrink(){
        String oldName = drink1.getName();
        double oldValue = drink1.getValue();
        String newName = "NewDrinkName123";
        double newValue = 1337;
        drink1.setValue(newValue);
        drink1.setName(newName);
        drinkService.save(drink1);
        Drink drinkFromDb = drinkService.get(drink1.getId());
        Assert.assertEquals(newName, drinkFromDb.getName());
        Assert.assertNotEquals(oldName, drinkFromDb.getName());
        Assert.assertEquals(newValue, drinkFromDb.getValue(),0.0001);
        Assert.assertNotEquals(oldValue, drinkFromDb.getValue());
        Assert.assertEquals(drink1.getId(), drinkFromDb.getId());
    }
    @Test
    public void whenGetByName_thenReturnDrink(){
        Drink newDrink = drinkService.getByName("TestDrink1");
        Assert.assertEquals(drink1, newDrink);
    }

    @Test
    public void whenDelete_thenRemovedFromDb_andReturnNull(){
        int id = drink1.getId();
        Assert.assertNotNull(drinkService.get(id));
        drinkService.delete(drink1);
        Drink drinkFromDb = drinkService.get(id);
        Assert.assertNull(drinkFromDb);
    }

    @Test
    public void whenSave_thenReturnDrink(){
        Drink actualDrink = new Drink("test1234", 9876);
        Drink newDrink = drinkService.save(actualDrink);
        Assert.assertEquals(actualDrink,newDrink);
    }
}
