package config;

import api.config.UserDetailsImpl;
import barkeep.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImplTest {
    private User user;
    private UserDetailsImpl userDetails;
    @Before
    public void setup(){
        user = new User("username", "password", "email");
        userDetails = new UserDetailsImpl(user);
    }

    @Test
    public void getPasswordTest(){
        Assert.assertEquals("password", userDetails.getPassword());
    }
    @Test
    public void getUserTest(){
        Assert.assertEquals(user, userDetails.getUser());
    }
    @Test
    public void getUsernameTest(){
        Assert.assertEquals("username", userDetails.getUsername());
    }
    @Test
    public void getAuthoritiesTest(){
        Assert.assertTrue(userDetails.getAuthorities().isEmpty());
    }
    @Test
    public void isEnabledTest(){
        Assert.assertTrue(userDetails.isEnabled());
    }
    @Test
    public void isCredentialsNonExpired(){
        Assert.assertTrue(userDetails.isCredentialsNonExpired());
    }
    @Test
    public void isAccountNonLocked(){
        Assert.assertTrue(userDetails.isAccountNonLocked());
    }
    @Test
    public void isAccountNonExpired(){
        Assert.assertTrue(userDetails.isAccountNonExpired());
    }
}
