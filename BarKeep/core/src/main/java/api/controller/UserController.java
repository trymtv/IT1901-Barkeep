package api.controller;

import barkeep.User;
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
    public List<User> getAll() {
        return userService.list();
    }

    @GetMapping("/search/{name}")
    public List<User> searchUsers(@PathVariable String name) {
        return userService.searchByUsername(name);
    }
}
