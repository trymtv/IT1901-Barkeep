package api.controller;

import barkeep.User;
import barkeep.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import database.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping("/register")
  public UserDto registerUser(@Valid @RequestBody User user) throws JsonProcessingException {
    User newUser = userService.add(user);
    return userService.convertToDto(newUser);
  }

  @GetMapping("/{userid}")
  public UserDto get(@PathVariable int userid) {
    User user = userService.get(userid);
    return userService.convertToDto(user);
  }

  @GetMapping("/all")
  public List<UserDto> getAll() {
    List<User> users = userService.list();
    return userService.convertListToDtos(users);
  }

  @GetMapping("/username/{username}")
  public UserDto getByUsername(@PathVariable String username) {
    User user = userService.getByUsername(username);
    return userService.convertToDto(user);
  }

  @GetMapping("/search/{name}")
  public List<UserDto> searchUsers(@PathVariable String name) {
    List<User> users = userService.searchByUsername(name);
    return userService.convertListToDtos(users);
  }
}
