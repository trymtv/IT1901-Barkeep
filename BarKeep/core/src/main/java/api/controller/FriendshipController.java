package api.controller;

import api.config.UserDetailsImpl;
import barkeep.Friendship;
import barkeep.FriendshipDTO;
import barkeep.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.FriendshipService;
import database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {
    @Autowired
    UserService userService;
    @Autowired
    FriendshipService friendshipService;


    @GetMapping(value = "/{userid}")
    public ResponseEntity getFriends(@PathVariable int userid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userService.get(userid);
        User loggedInUser = userDetails.getUser();

        if (userService.isSameUser(user, loggedInUser)) {
            List<User> users = friendshipService.getFriends(user);
            return ResponseEntity.ok().body(userService.convertListToDTOs(users));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
    }

    @PostMapping("/")
    public FriendshipDTO addFriendship(@RequestBody FriendshipDTO friendshipDTO) throws JsonProcessingException {
        //TODO: Check that user is one of friends
        System.out.println(new ObjectMapper().writeValueAsString(friendshipDTO));
        Friendship friendship = friendshipService.addFriendship(friendshipDTO);
        System.out.println(friendship);
        return friendshipService.convertToDTO(friendship);
    }

    @DeleteMapping("/removefriend/{userid}")
    public void removeFriendship(@PathVariable int userid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //TODO: Check that user is one of friends
        User user = userService.get(userid);
        User loggedInUser = userDetails.getUser();
        friendshipService.removeByUsers(user, loggedInUser);
    }
}
