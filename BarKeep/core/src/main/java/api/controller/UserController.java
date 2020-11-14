package api.controller;

import barkeep.User;
import barkeep.UserDTO;
import database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        List<User> users = userService.list();
        return userService.convertListToDTOs(users);
    }

    @GetMapping("/search/{name}")
    public List<UserDTO> searchUsers(@PathVariable String name) {
       List<User> users =  userService.searchByUsername(name);
       return userService.convertListToDTOs(users);
    }
}
