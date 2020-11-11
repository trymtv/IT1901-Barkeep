package coretest;

import barkeep.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

