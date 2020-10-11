package coretest;

import barkeep.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user1;

    @BeforeEach
    public void setupUser(){
        user1 = new User(0,"Ola", "olakul98", "Høst2005", "olacool@osloskolen.no");
    }

    @Test
    public void testUserChange(){
        user1.setName("Kari");
        testUserGet("Kari");
    }

    private void testUserGet(String name){
        assertEquals(name, user1.getName());
        assertEquals(name, user1.toString());
    }
}
