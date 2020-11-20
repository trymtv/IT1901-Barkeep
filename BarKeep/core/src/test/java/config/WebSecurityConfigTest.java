package config;

import static org.mockito.BDDMockito.given;


import api.config.WebSecurityConfig;
import barkeep.User;
import database.HibernateDrinkRepository;
import database.HibernateFriendshipRepository;
import database.HibernateIOweYouRepository;
import database.HibernateUserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebSecurityConfig.class)
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
public class WebSecurityConfigTest {

  @Autowired
  WebSecurityConfig webSecurityConfig;
  @MockBean
  HibernateUserRepository userRepository;
  @MockBean
  HibernateIOweYouRepository iOweYouRepository;
  @MockBean
  HibernateFriendshipRepository friendshipRepository;
  @MockBean
  HibernateDrinkRepository drinkRepository;

  @Before
  public void setup() {
    User user1 = new User("username", "test", "test");
    given(userRepository.findByUsername("username")).willReturn(user1);
  }

  @Test
  public void passwordEncoderTest() {
    final BCryptPasswordEncoder encoder = webSecurityConfig.passwordEncoder();
    final String encodedPassword = encoder.encode("password");
    Assert.assertNotEquals("password", encodedPassword);
    Assert.assertNotNull(encodedPassword);
  }

  @Test
  public void userDetailsServiceTest() {
    final UserDetailsService userDetailsService = webSecurityConfig.userDetailsService();
    UserDetails userDetails = userDetailsService.loadUserByUsername("username");
    Assert.assertNotNull(userDetails);
    Assert.assertNotNull(userDetails.getAuthorities());
    Assert.assertEquals("username", userDetails.getUsername());
  }

  @Test
  public void authenticationProviderTest() {
    final DaoAuthenticationProvider authenticationProvider =
        webSecurityConfig.authenticationProvider();
    Assert.assertNotNull(authenticationProvider);
  }
}
