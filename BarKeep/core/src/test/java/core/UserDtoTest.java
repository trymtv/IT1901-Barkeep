package core;

import barkeep.UserDto;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDtoTest {
  private UserDto user1;

  @BeforeEach
  public void setupUser() {
    user1 = new UserDto(1, "user1", "olacool@osloskolen.no");
  }

  @Test
  public void testIdChange() {
    user1.setId(420);
    Assert.assertEquals(420, user1.getId());
  }


  @Test
  public void testUserUsernameChange() {
    user1.setUsername("karikul00");
    Assert.assertEquals("karikul00", user1.getUsername());
  }

  @Test
  public void testUserEmailChange() {
    user1.setEmail("kari@gmail.com");
    Assert.assertEquals("kari@gmail.com", user1.getEmail());
  }
}

