package database;

import barkeep.Friendship;
import barkeep.FriendshipDTO;
import barkeep.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    @Autowired
    HibernateFriendshipRepository friendshipRepository;
    @Autowired
    UserService userService;

    public Friendship get(FriendshipDTO friendshipDTO){
        return friendshipRepository.findById(friendshipDTO.getId()).orElse(null);
    }
    public List<Friendship> getFriendships(User user){
        return friendshipRepository.getFriendshipByUser1OrUser2(user, user);
    }
    public List<User> getFriends(User user){
        return getFriendships(user).stream()
                //Gets user from friendship that's not itself
                .map(fs -> fs.getUser1() == user ? fs.getUser2() : fs.getUser1())
                .collect(Collectors.toList());
    }
    public Friendship addFriendship(FriendshipDTO friendshipDTO){
        User user1 = userService.get(friendshipDTO.getUser1());
        User user2 = userService.get(friendshipDTO.getUser2());
        Friendship friendship = new Friendship(user1,user2);
        return add(friendship);
    }
    public Friendship addFriendship(User user1, User user2){
        Friendship friendship = new Friendship();
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        return friendshipRepository.save(friendship);
    }
    public Friendship add(Friendship friendship){
        return friendshipRepository.save(friendship);
    }
    public void remove(Friendship friendship){
        User user1 = friendship.getUser1();
        User user2 = friendship.getUser2();
        Friendship correctFriendship = friendshipRepository.getFriendshipBetween(user1,user2);
        friendshipRepository.delete(correctFriendship);
    }
    public void remove(FriendshipDTO friendshipDTO){
        Friendship friendship = get(friendshipDTO);
        friendshipRepository.delete(friendship);
    }
    public FriendshipDTO convertToDTO(Friendship friendship){
        return new FriendshipDTO(friendship);
    }
}
