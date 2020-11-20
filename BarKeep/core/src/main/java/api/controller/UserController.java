package api.controller;

import barkeep.User;
import barkeep.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody User user) throws JsonProcessingException {
        User newUser = userService.add(user);
        return userService.convertToDTO(newUser);
    }

    @GetMapping("/{userid}")
    public UserDTO get(@PathVariable int userid) {
        User user = userService.get(userid);
        return userService.convertToDTO(user);
    }

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        List<User> users = userService.list();
        return userService.convertListToDTOs(users);
    }

    @GetMapping("/username/{username}")
    public UserDTO getByUsername(@PathVariable String username) {
        User user = userService.getByUsername(username);
        return userService.convertToDTO(user);
    }

    @GetMapping("/search/{name}")
    public List<UserDTO> searchUsers(@PathVariable String name) {
        List<User> users = userService.searchByUsername(name);
        return userService.convertListToDTOs(users);
    }
}
