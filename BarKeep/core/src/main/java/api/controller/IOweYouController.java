package api.controller;

import barkeep.IOweYou;
import barkeep.IOweYouDTO;
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
    public List<IOweYouDTO> userOwes(@PathVariable int userId){
        //TODO: Check that user is logged in and has id == userId
        User user = userService.get(userId);
        List<IOweYou> userOwes = iOweYouService.userOwes(user);
        return iOweYouService.convertListToDTOs(userOwes);
    }

    @GetMapping("/{userId}/owesuser")
    public List<IOweYouDTO> owesUser(@PathVariable int userId){
        //TODO: Check that user is logged in and has id == userId
        User user = userService.get(userId);
        List<IOweYou> owesUser = iOweYouService.owesUser(user);
        return iOweYouService.convertListToDTOs(owesUser);
    }

    @PostMapping("/")
    public void addIOweYou(@RequestBody IOweYouDTO iOweYou){
        //TODO: Check that user is Owner of IOweYou
        iOweYouService.add(iOweYou);
    }

    @DeleteMapping("/{iOweYouId}")
    public void deleteIOweYou(@PathVariable int iOweYouId){
        //TODO: Check that user is Owner of IOweYou
        IOweYou iOweYou = iOweYouService.get(iOweYouId);
        iOweYouService.delete(iOweYou);
    }

}
