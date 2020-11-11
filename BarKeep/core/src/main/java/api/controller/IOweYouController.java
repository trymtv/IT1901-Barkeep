package api.controller;

import barkeep.IOweYou;
import barkeep.User;
import database.IOweYouService;
import database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ioweyou")
public class IOweYouController {
    @Autowired
    IOweYouService iOweYouService;
    @Autowired
    UserService userService;

    @GetMapping("/{userId}/userowes")
    public List<IOweYou> userOwes(@PathVariable int userId){
        User user = userService.get(userId);
        return iOweYouService.userOwes(user);
    }

    @GetMapping("/{userId}/owesuser")
    public List<IOweYou> owesUser(@PathVariable int userId){
        User user = userService.get(userId);
        return iOweYouService.owesUser(user);
    }

    @PostMapping("/")
    public void addIOweYou(@RequestBody IOweYou iOweYou){
        iOweYouService.add(iOweYou);
    }

    @DeleteMapping("/{iOweYouId}")
    public void deleteIOweYou(@PathVariable int iOweYouId){
        IOweYou iOweYou = iOweYouService.get(iOweYouId);
        iOweYouService.delete(iOweYou);
    }

}
