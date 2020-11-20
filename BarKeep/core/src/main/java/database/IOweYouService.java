package database;

import barkeep.IOweYou;
import barkeep.IOweYouDto;
import barkeep.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOweYouService {
  @Autowired
  HibernateIOweYouRepository iOweYouRepository;
  @Autowired
  UserService userService;

  public IOweYou get(int id) {
    return iOweYouRepository.findById(id).orElse(null);
  }

  public List<IOweYou> owesUser(User user) {
    return iOweYouRepository.findAllByOwner(user);
  }

  public List<IOweYou> userOwes(User user) {
    return iOweYouRepository.findAllByUser(user);
  }

  /**
   * Adds the IOweYou given a IOweYouDTO.
   *
   * @param iOweYouDTO the IOweYou to be added.
   * @return the IOweYou that was added.
   */
  public IOweYou add(IOweYouDto iOweYouDTO) {
    User owner = userService.get(iOweYouDTO.getOwner());
    User user = userService.get(iOweYouDTO.getUser());
    IOweYou iou = new IOweYou(owner, user, iOweYouDTO.getDrink());
    iou.setTime(iOweYouDTO.getTime());
    return add(iou);
  }

  public IOweYou add(IOweYou iOweYou) {
    return iOweYouRepository.save(iOweYou);
  }

  public void delete(IOweYou iOweYou) {
    iOweYouRepository.delete(iOweYou);
  }

  public IOweYouDto convertToDTO(IOweYou iOweYou) {
    return new IOweYouDto(iOweYou);
  }

  public List<IOweYouDto> convertListToDTOs(List<IOweYou> iOweYous) {
    return iOweYous.stream().map(iou -> new IOweYouDto(iou))
        .collect(Collectors.toList());
  }
}
