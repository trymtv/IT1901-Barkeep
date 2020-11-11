package database;

import barkeep.Drink;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = DrinkService.class)
public class DrinkServiceTest {
    @Autowired
    private DrinkService drinkService;
    @MockBean
    private HibernateDrinkRepository drinkRepository;

    private Drink drink1;
    private Drink drink2;

    @Before
    public void setUp() {
        drink1 = new Drink("TestDrink1", 12);
        drink2 = new Drink("TestDrink2", 34);
    }

    @Test
    public void whenList_thenContainsDrinks() {
        given(drinkRepository.findAll()).willReturn(Arrays.asList(drink1, drink2));
        List<Drink> drinks = drinkService.list();
        Assert.assertTrue(drinks.contains(drink1));
        Assert.assertTrue(drinks.contains(drink2));
    }

    @Test
    public void whenGet_thenReturnDrink() {
        given(drinkRepository.findById(drink1.getId())).willReturn(Optional.of(drink1));
        Drink newDrink = drinkService.get(drink1.getId());
        Assert.assertEquals(drink1, newDrink);
    }

    @Test
    public void whenSearchByName_notCaseSensitive_thenReturnListOfDrinks() {
        given(drinkRepository.findByNameIgnoreCaseContaining("testdrink"))
                .willReturn(Arrays.asList(drink1, drink2));
        List<Drink> drinks = drinkService.searchByName("testdrink");
        Assert.assertTrue(drinks.contains(drink1));
        Assert.assertTrue(drinks.contains(drink2));
    }

    @Test
    public void whenDrinkUpdateSave_thenReturnUpdatedDrink() {
        String oldName = drink1.getName();
        double oldValue = drink1.getValue();
        String newName = "NewDrinkName123";
        double newValue = 1337;
        drink1.setValue(newValue);
        drink1.setName(newName);
        given(drinkRepository.save(any(Drink.class))).willAnswer(i -> i.getArgument(0));
        given(drinkRepository.findById(drink1.getId())).willReturn(Optional.of(drink1));
        drinkService.save(drink1);
        Drink drinkFromDb = drinkService.get(drink1.getId());
        Assert.assertEquals(newName, drinkFromDb.getName());
        Assert.assertNotEquals(oldName, drinkFromDb.getName());
        Assert.assertEquals(newValue, drinkFromDb.getValue(), 0.0001);
        Assert.assertNotEquals(oldValue, drinkFromDb.getValue());
        Assert.assertEquals(drink1.getId(), drinkFromDb.getId());
    }

    @Test
    public void whenGetByName_thenReturnDrink() {
        given(drinkRepository.findFirstByName("TestDrink1")).willReturn(drink1);
        Drink newDrink = drinkService.getByName("TestDrink1");
        Assert.assertEquals(drink1, newDrink);
    }

    @Test
    public void whenDelete_thenRemovedFromDb_andReturnNull() {
        int id = drink1.getId();
        given(drinkRepository.findById(id)).willReturn(Optional.of(drink1)).willReturn(Optional.empty());
        Assert.assertNotNull(drinkService.get(id));
        drinkService.delete(drink1);
        Drink drinkFromDb = drinkService.get(id);
        Assert.assertNull(drinkFromDb);
    }

    @Test
    public void whenSave_thenReturnDrink() {
        given(drinkRepository.save(any(Drink.class))).willAnswer(i -> i.getArgument(0));
        Drink actualDrink = new Drink("test1234", 9876);
        Drink newDrink = drinkService.save(actualDrink);
        Assert.assertEquals(actualDrink, newDrink);
    }
}
