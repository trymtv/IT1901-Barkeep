package core;

import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user1;

    @BeforeEach
    public void setupUser() {
        user1 = new User(0,  "olakul98", "Høst2005", "olacool@osloskolen.no");
    }

    @Test
    public void testIdChange() {
        user1.setId(420);
        testUserId(420);
    }


    @Test
    public void testUserUsernameChange() {
        user1.setUsername("karikul00");
        testUserUsername("karikul00");
    }

    @Test
    public void testUserEmailChange() {
        user1.setEmail("kari@gmail.com");
        testUserEmail("kari@gmail.com");
    }

    @Test
    public void testUserPasswordChange() {
        user1.setPassword("Olaerkul12");
        testUserPassword("Olaerkul12");
    }

    @Test
    public void testAddIOweYouAndRemoveIOU() {
        IOweYou iou = new IOweYou(new User(2, "perolav"), new User(1, "per"), new Drink("Water", 20.0));
        user1.addIOweYou(iou);
        user1.addIOweYou(iou);
        user1.removeIOweYou(iou);
    }


    private void testUserId(int id) {
        assertEquals(id, user1.getId());
    }


    private void testUserUsername(String username) {
        assertEquals(username, user1.getUsername());
    }

    private void testUserEmail(String email) {
        assertEquals(email, user1.getEmail());
    }

    private void testUserPassword(String password) {
        assertEquals(password, user1.getPassword());
    }
}

