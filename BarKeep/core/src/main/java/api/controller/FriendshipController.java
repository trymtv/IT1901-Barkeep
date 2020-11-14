package api.controller;

import barkeep.Friendship;
import barkeep.User;
import database.FriendshipService;
import database.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {
    @Autowired
    UserService userService;
    @Autowired
    FriendshipService friendshipService;

    @GetMapping("/{userid}")
    public List<User> getFriends(@PathVariable int userid) {
        //TODO: Check that the user is allowed (a.k.a. asking for own friends)
        User user = userService.get(userid);
        return friendshipService.getFriends(user);
    }

    @PostMapping("/")
    public Friendship addFriendship(@RequestBody Friendship friendship) {
        //TODO: Check that user is one of friends
        return friendshipService.add(friendship);
    }

    @DeleteMapping("/")
    public void removeFriendship(@RequestBody Friendship friendship) {
        //TODO: Check that user is one of friends
        friendshipService.remove(friendship);
    }
}
