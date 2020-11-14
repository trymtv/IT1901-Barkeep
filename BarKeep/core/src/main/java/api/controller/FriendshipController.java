package api.controller;

import barkeep.Friendship;
import barkeep.FriendshipDTO;
import barkeep.User;
import barkeep.UserDTO;
import database.FriendshipService;
import database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {
    @Autowired
    UserService userService;
    @Autowired
    FriendshipService friendshipService;

    @GetMapping("/{userid}")
    public List<UserDTO> getFriends(@PathVariable int userid) {
        //TODO: Check that the user is allowed (a.k.a. asking for own friends)
        User user = userService.get(userid);
        List<User> users = friendshipService.getFriends(user);
        return userService.convertListToDTOs(users);
    }
    @PostMapping("/")
    public FriendshipDTO addFriendship(@RequestBody FriendshipDTO friendshipDTO){
        //TODO: Check that user is one of friends
        Friendship friendship = friendshipService.addFriendship(friendshipDTO);
        return friendshipService.convertToDTO(friendship);
    }
    @DeleteMapping("/")
    public void removeFriendship(@RequestBody Friendship friendship) {
        //TODO: Check that user is one of friends
        friendshipService.remove(friendship);
    }
}
