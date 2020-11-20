package database;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;


import barkeep.Friendship;
import barkeep.FriendshipDto;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = FriendshipService.class)
public class FriendshipServiceTest {
  @Autowired
  private FriendshipService friendshipService;
  @MockBean
  private HibernateFriendshipRepository friendshipRepository;
  @MockBean
  private UserService userService;

  private User user1;
  private User user2;
  private Friendship friendship;

  @Before
  public void setUp() {
    user1 = new User("user1", "d", "h@h.com");
    user1.setId(1);
    user2 = new User("user2", "d", "h2@h.com");
    user2.setId(2);
    friendship = new Friendship();
    friendship.setUser1(user1);
    friendship.setUser2(user2);
    given(friendshipRepository.getFriendshipBetween(user1, user2)).willReturn(friendship);
    given(friendshipRepository.getFriendshipBetween(user2, user1)).willReturn(friendship);
    given(friendshipRepository.getFriendshipByUser1OrUser2(user1, user1))
        .willReturn(Arrays.asList(friendship));
    given(friendshipRepository.getFriendshipByUser1OrUser2(user2, user2))
        .willReturn(Arrays.asList(friendship));
    given(friendshipRepository.save(any(Friendship.class))).will(i -> i.getArgument(0));
  }

  @Test
  public void whenGetFriendships_returnFriendships() {
    List<Friendship> friendships1 = friendshipService.getFriendships(user1);
    List<Friendship> friendships2 = friendshipService.getFriendships(user2);
    Assert.assertEquals(friendships1, friendships2);
    Assert.assertTrue(friendships1.contains(friendship));
  }

  @Test
  public void whenGetFriends_returnUsers() {
    List<User> friends1 = friendshipService.getFriends(user1);
    List<User> friends2 = friendshipService.getFriends(user2);
    Assert.assertTrue(friends1.contains(user2));
    Assert.assertFalse(friends1.contains(user1));
    Assert.assertTrue(friends2.contains(user1));
    Assert.assertFalse(friends2.contains(user2));
    Assert.assertNotEquals(friends1, friends2);
  }

  @Test
  public void whenAdd_thenReturnFriendship() {
    Friendship fshipSaved = friendshipService.add(friendship);
    Assert.assertEquals(friendship, fshipSaved);
  }

  @Test
  public void whenAddFriendship_thenReturnFriendship() {
    Friendship fship = friendshipService.addFriendship(user1, user2);
    Assert.assertEquals(user1, fship.getUser1());
    Assert.assertEquals(user2, fship.getUser2());
  }

  @Test
  public void whenRemove_thenCallDeleteOnRepository() {
    friendshipService.remove(friendship);
    verify(friendshipRepository).delete(any(Friendship.class));
  }

  @Test
  public void whenAddFriendShipDTO_thenReturnFriendship() {
    given(userService.get(any(UserDto.class))).willReturn(user1).willReturn(user2);
    given(friendshipRepository.getFriendshipBetween(user1, user2)).willReturn(null);
    FriendshipDto friendshipDTO = new FriendshipDto(friendship);
    Friendship fromDb = friendshipService.addFriendship(friendshipDTO);
    Assert.assertNotNull(fromDb);
    Assert.assertEquals(user1, fromDb.getUser1());
    Assert.assertEquals(user2, fromDb.getUser2());
  }

  @Test
  public void whenRemoveByUsers_thenCallDelete() {
    friendshipService.removeByUsers(user1, user2);
    verify(friendshipRepository).delete(friendship);
  }

  @Test
  public void whenRemoveDTO_thenCallDelete() {
    given(friendshipRepository.findById(any())).willReturn(Optional.of(friendship));
    FriendshipDto friendshipDTO = new FriendshipDto(friendship);
    friendshipService.remove(friendshipDTO);
    verify(friendshipRepository).delete(friendship);
  }

  @Test
  public void whenGetByDTO_thenReturnFriendship() {
    given(friendshipRepository.findById(any())).willReturn(Optional.of(friendship));
    FriendshipDto friendshipDTO = new FriendshipDto(friendship);
    Friendship fromDb = friendshipService.get(friendshipDTO);
    Assert.assertEquals(friendship, fromDb);
  }
}
