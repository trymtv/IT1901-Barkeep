package database;

import barkeep.*;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = IOweYouService.class)
public class IOweYouServiceTest {
    @Autowired
    private IOweYouService iOweYouService;
    @MockBean
    private HibernateIOweYouRepository iOweYouRepository;
    @MockBean
    private UserService userService;

    private User user1;
    private User user2;
    private User user3;
    private IOweYou iou1;
    private IOweYou iou2;

    @Before
    public void setUp() {
        Drink drink = new Drink("testdrink", 123);
        user1 = new User("Testuser1",  "d", "h@h.com");
        user2 = new User("Testuser2", "d", "h2@h.com");
        user3 = new User("Testuser3", "d", "h2@h.com");
        iou1 = new IOweYou(user1, user2, drink);
        iou2 = new IOweYou(user2, user3, drink);
    }

    @Test
    public void whenOwesUser_thenReturnIOweYous(){
        given(iOweYouRepository.findAllByOwner(user1)).willReturn(Arrays.asList(iou1));
        List<IOweYou> ious = iOweYouService.owesUser(user1);
        Assert.assertFalse(ious.isEmpty());
        Assert.assertTrue(ious.contains(iou1));
        Assert.assertFalse(ious.contains(iou2));
    }
    @Test
    public void whenUserOwes_thenReturnIOweYous(){
        given(iOweYouRepository.findAllByUser(user2)).willReturn(Arrays.asList(iou1));
        List<IOweYou> ious = iOweYouService.userOwes(user2);
        Assert.assertFalse(ious.isEmpty());
        Assert.assertTrue(ious.contains(iou1));
        Assert.assertFalse(ious.contains(iou2));
    }
    @Test
    public void whenAdd_withDTO_thenReturnIOweYou(){
        given(iOweYouRepository.save(any())).willAnswer(i->i.getArgument(0));
        UserDTO owner = new UserDTO(user1);
        UserDTO user = new UserDTO(user2);
        given(userService.get(any())).willReturn(user1).willReturn(user2);
        IOweYouDTO iOweYouDTO = new IOweYouDTO(iou1);
        IOweYou newIOweYou = iOweYouService.add(iOweYouDTO);
        assertIOweYouEqual(iou1, newIOweYou);
    }
    private void assertIOweYouEqual(IOweYou expected, IOweYou actual){
        Assert.assertEquals(expected.getId(), actual .getId());
        Assert.assertEquals(expected.getDrink(), actual.getDrink());
        Assert.assertEquals(expected.getOwner(), actual.getOwner());
        Assert.assertEquals(expected.getUser(), actual.getUser());
        Assert.assertEquals(expected.getTime(), actual.getTime());
    }

    private void AssertDTOEqual(IOweYou iOweYou, IOweYouDTO iOweYouDTO){
        Assert.assertEquals(iOweYou.getId(), iOweYouDTO.getId());
        Assert.assertEquals(iOweYou.getDrink(), iOweYouDTO.getDrink());
        UserServiceTest.assertUserEqualDTO(iOweYou.getOwner(), iOweYouDTO.getOwner());
        UserServiceTest.assertUserEqualDTO(iOweYou.getUser(), iOweYouDTO.getUser());
        Assert.assertEquals(iOweYou.getTime(), iOweYouDTO.getTime());
    }
}
