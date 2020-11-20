package database;

import barkeep.Friendship;
import barkeep.FriendshipDto;
import barkeep.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {
  @Autowired
  HibernateFriendshipRepository friendshipRepository;
  @Autowired
  UserService userService;

  public Friendship get(FriendshipDto friendshipDto) {
    return friendshipRepository.findById(friendshipDto.getId()).orElse(null);
  }

  public List<Friendship> getFriendships(User user) {
    return friendshipRepository.getFriendshipByUser1OrUser2(user, user);
  }

  /**
   * Gets the friends from a user if the user is in either of the fields.
   *
   * @param user the user to get the friends for.
   * @return a list of users that is the users friends.
   */
  public List<User> getFriends(User user) {
    return getFriendships(user).stream()
        .map(fs -> fs.getUser1() == user ? fs.getUser2() : fs.getUser1())
        .collect(Collectors.toList());
  }

  /**
   * Adds a given friendship given a frienship.
   *
   * @param friendshipDto the given friendship.
   * @return  the friendship added.
   */
  public Friendship addFriendship(FriendshipDto friendshipDto) {
    User user1 = userService.get(friendshipDto.getUser1());
    User user2 = userService.get(friendshipDto.getUser2());
    return addFriendship(user1, user2);
  }

  /**
   * Adds the given friendship defined by the two parts.
   *
   * @param user1 the first user.
   * @param user2 the second user.
   * @return the friendship added.
   */
  public Friendship addFriendship(User user1, User user2) {
    Friendship friendship = new Friendship();
    friendship.setUser1(user1);
    friendship.setUser2(user2);
    return add(friendship);
  }

  /**
   * Adds a friendship defined by the given friendship.
   *
   * @param friendship the given friendship
   * @return the friendship added.
   */
  public Friendship add(Friendship friendship) {
    Friendship friendshipFromDb = friendshipRepository
        .getFriendshipBetween(friendship.getUser1(), friendship.getUser2());

    if (friendshipFromDb != null) {
      return friendshipFromDb;
    }
    return friendshipRepository.save(friendship);
  }

  /**
   * Removes the friendship.
   *
   * @param friendship the friendship to remove.
   */
  public void remove(Friendship friendship) {
    User user1 = friendship.getUser1();
    User user2 = friendship.getUser2();
    Friendship correctFriendship = friendshipRepository.getFriendshipBetween(user1, user2);
    friendshipRepository.delete(correctFriendship);
  }

  /**
   * Removes the friendship.
   *
   * @param friendshipDto the friendship to remove.
   */
  public void remove(FriendshipDto friendshipDto) {
    Friendship friendship = get(friendshipDto);
    friendshipRepository.delete(friendship);
  }

  public FriendshipDto convertToDto(Friendship friendship) {
    return new FriendshipDto(friendship);
  }

  public void removeByUsers(User user1, User user2) {
    Friendship friendship = friendshipRepository.getFriendshipBetween(user1, user2);
    friendshipRepository.delete(friendship);
  }
}
