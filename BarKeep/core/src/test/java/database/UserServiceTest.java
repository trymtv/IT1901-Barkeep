package database;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


import barkeep.User;
import barkeep.UserDto;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = UserService.class)
public class UserServiceTest {
  @Autowired
  private UserService userService;
  @MockBean
  private HibernateUserRepository userRepository;
  @MockBean
  private PasswordEncoder passwordEncoder;

  private User user1;
  private User user2;

  public static void assertUserEqualDTO(User user, UserDto userDTO) {
    Assert.assertEquals(user.getId(), userDTO.getId());
    Assert.assertEquals(user.getUsername(), userDTO.getUsername());
    Assert.assertEquals(user.getEmail(), userDTO.getEmail());
  }

  @Before
  public void setUp() {
    user1 = new User("user1", "d", "h@h.com");
    user1.setId(1);
    user2 = new User("user2", "d", "h2@h.com");
    user2.setId(2);
  }

  @Test
  public void whenList_thenContainsUsers() {
    given(userRepository.findAll()).willReturn(Arrays.asList(user1, user2));
    List<User> users = userService.list();
    Assert.assertTrue(users.contains(user1));
    Assert.assertTrue(users.contains(user2));
  }

  @Test
  public void whenSearchByName_notCaseSensitive_thenReturnListOfUsers() {
    given(userRepository.findByUsernameIgnoreCaseContaining("testuser"))
        .willReturn(Arrays.asList(user1, user2));
    List<User> users = userService.searchByUsername("testuser");
    Assert.assertTrue(users.contains(user1));
    Assert.assertTrue(users.contains(user2));
  }

  @Test
  public void whenConvertToDTO_thenReturnUserDTO() {
    UserDto userDTO = userService.convertToDto(user1);
    assertUserEqualDTO(user1, userDTO);
  }

  @Test
  public void whenConvertListToDTOS_thenReturnUserDTOs() {
    List<UserDto> userDtos = userService.convertListToDtos(Arrays.asList(user1, user2));
    assertUserEqualDTO(user1, userDtos.get(0));
    assertUserEqualDTO(user2, userDtos.get(1));
  }

  @Test
  public void whenAddUser_thenReturnUser() {
    given(userRepository.save(any())).willAnswer(i -> i.getArgument(0));
    User savedUser = userService.add(user1);
    Assert.assertEquals(user1, savedUser);
  }

  @Test
  public void whenGetById_thenReturnUser() {
    given(userRepository.findById(1)).willReturn(Optional.of(user1));
    User fromDb = userService.get(1);
    Assert.assertEquals(user1, fromDb);
  }

  @Test
  public void whenIsSameUser_thenReturnBoolean() {
    Assert.assertFalse(userService.isSameUser(user1, user2));
    Assert.assertTrue(userService.isSameUser(user1, user1));
  }

  @Test
  public void whenGetByUsername_thenReturnUser() {
    given(userRepository.findByUsername("user1")).willReturn(user1);
    User fromDb = userService.getByUsername("user1");
    Assert.assertEquals(user1, fromDb);
  }

  @Test
  public void whenGetUserDTO_thenReturnUser() {
    given(userRepository.findById(1)).willReturn(Optional.of(user1));
    UserDto userDto = new UserDto(user1);
    User fromDb = userService.get(userDto);
    Assert.assertEquals(user1, fromDb);
  }
}
