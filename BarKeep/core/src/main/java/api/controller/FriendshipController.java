package api.controller;

import api.config.UserDetailsImpl;
import barkeep.Friendship;
import barkeep.FriendshipDto;
import barkeep.User;
import database.FriendshipService;
import database.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {
  @Autowired
  UserService userService;
  @Autowired
  FriendshipService friendshipService;

  /**
   * Returns the given list of friends with the given user is and the given authentication.
   *
   * @param userid the user id.
   * @param userDetails the authentication.
   * @return  the list of users.
   */
  @GetMapping(value = "/{userid}")
  public ResponseEntity getFriends(@PathVariable int userid,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userService.get(userid);
    User loggedInUser = userDetails.getUser();

    if (userService.isSameUser(user, loggedInUser)) {
      List<User> users = friendshipService.getFriends(user);
      return ResponseEntity.ok().body(userService.convertListToDtos(users));
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
  }

  /**
   * Adds a friendship given by the friendshipDto.
   *
   * @param friendshipDto the friendship to be added.
   * @return the friendship that was added
   */
  @PostMapping("/")
  public FriendshipDto addFriendship(@RequestBody FriendshipDto friendshipDto) {
    Friendship friendship = friendshipService.addFriendship(friendshipDto);
    return friendshipService.convertToDto(friendship);
  }

  /**
   * Removes the friend from the logged in user.
   *
   * @param userid the other user in the friendships id.
   * @param userDetails the authentication.
   */
  @DeleteMapping("/removefriend/{userid}")
  public void removeFriendship(@PathVariable int userid,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userService.get(userid);
    User loggedInUser = userDetails.getUser();
    friendshipService.removeByUsers(user, loggedInUser);
  }
}
