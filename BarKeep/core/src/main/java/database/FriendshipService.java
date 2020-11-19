package database;

import barkeep.Friendship;
import barkeep.FriendshipDTO;
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

    public Friendship get(FriendshipDTO friendshipDTO) {
        return friendshipRepository.findById(friendshipDTO.getId()).orElse(null);
    }

    public List<Friendship> getFriendships(User user) {
        return friendshipRepository.getFriendshipByUser1OrUser2(user, user);
    }

    public List<User> getFriends(User user) {
        return getFriendships(user).stream()
                //Gets user from friendship that's not itself
                .map(fs -> fs.getUser1() == user ? fs.getUser2() : fs.getUser1())
                .collect(Collectors.toList());
    }

    public Friendship addFriendship(FriendshipDTO friendshipDTO) {
        User user1 = userService.get(friendshipDTO.getUser1());
        User user2 = userService.get(friendshipDTO.getUser2());
        return addFriendship(user1, user2);
    }

    public Friendship addFriendship(User user1, User user2) {
        Friendship friendship = new Friendship();
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        return add(friendship);
    }

    public Friendship add(Friendship friendship) {
        Friendship friendshipFromDb = friendshipRepository
                .getFriendshipBetween(friendship.getUser1(), friendship.getUser2());

        if (friendshipFromDb != null) {
            return friendshipFromDb;
        }
        return friendshipRepository.save(friendship);
    }

    public void remove(Friendship friendship) {
        User user1 = friendship.getUser1();
        User user2 = friendship.getUser2();
        Friendship correctFriendship = friendshipRepository.getFriendshipBetween(user1, user2);
        friendshipRepository.delete(correctFriendship);
    }

    public void remove(FriendshipDTO friendshipDTO) {
        Friendship friendship = get(friendshipDTO);
        friendshipRepository.delete(friendship);
    }

    public FriendshipDTO convertToDTO(Friendship friendship) {
        return new FriendshipDTO(friendship);
    }

    public void removeByUsers(User user1, User user2) {
        Friendship friendship = friendshipRepository.getFriendshipBetween(user1, user2);
        friendshipRepository.delete(friendship);
    }
}
