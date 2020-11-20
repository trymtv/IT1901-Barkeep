package api.controller;

import barkeep.IOweYou;
import barkeep.IOweYouDto;
import barkeep.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import database.IOweYouService;
import database.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ioweyou")
public class IOweYouController {
  @Autowired
  IOweYouService iOweYouService;
  @Autowired
  UserService userService;

  /**
   * Gets a list of IOweYous given by the user that is being owed drinks.
   *
   * @param userId the defined user
   * @return a list of IOweYous
   */
  @GetMapping("/{userId}/userowes")
  public List<IOweYouDto> userOwes(@PathVariable int userId) {
    User user = userService.get(userId);
    List<IOweYou> userOwes = iOweYouService.userOwes(user);
    return iOweYouService.convertListToDTOs(userOwes);
  }

  /**
   * Gets a list of IOweYous given by the user that is owing drinks.
   *
   * @param userId the defined user
   * @return a list of IOweYous
   */
  @GetMapping("/{userId}/owesuser")
  public List<IOweYouDto> owesUser(@PathVariable int userId) {
    User user = userService.get(userId);
    List<IOweYou> owesUser = iOweYouService.owesUser(user);
    return iOweYouService.convertListToDTOs(owesUser);
  }

  /**
   * Adds a IOweYou given a IOweYou.
   *
   * @param iOweYou the IOweYou to be added.
   * @throws JsonProcessingException
   */
  @PostMapping("/")
  public void addIOweYou(@RequestBody IOweYouDto iOweYou) {
    iOweYouService.add(iOweYou);

  }

  /**
   * Deletes the IOweYou defined by the IOweYous id.
   *
   * @param iOweYouId the id to be deleted.
   */
  @DeleteMapping("/{iOweYouId}")
  public void deleteIOweYou(@PathVariable int iOweYouId) {
    IOweYou iOweYou = iOweYouService.get(iOweYouId);
    iOweYouService.delete(iOweYou);
  }

}
