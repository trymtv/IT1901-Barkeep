package database;

import barkeep.IOweYou;
import barkeep.IOweYouDTO;
import barkeep.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IOweYouService {
    @Autowired
    HibernateIOweYouRepository iOweYouRepository;
    @Autowired
    UserService userService;

    public IOweYou get(int id){
        return iOweYouRepository.findById(id).orElse(null);
    }

    public List<IOweYou> owesUser(User user){
        return iOweYouRepository.findAllByOwner(user);
    }

    public List<IOweYou> userOwes(User user){
        return iOweYouRepository.findAllByUser(user);
    }

    public IOweYou add(IOweYouDTO iOweYouDTO){
        User owner = userService.get(iOweYouDTO.getOwner());
        User user = userService.get(iOweYouDTO.getUser());
        IOweYou iou = new IOweYou(owner,user,iOweYouDTO.getDrink());
        iou.setTime(iOweYouDTO.getTime());

        return add(iou);
    }
    public IOweYou add(IOweYou iOweYou) {
        return iOweYouRepository.save(iOweYou);
    }
    public void delete(IOweYou iOweYou) {
        iOweYouRepository.delete(iOweYou);
    }

    public IOweYouDTO convertToDTO(IOweYou iOweYou){
        return new IOweYouDTO(iOweYou);
    }
    public List<IOweYouDTO> convertListToDTOs(List<IOweYou> iOweYous){
        return iOweYous.stream()
                    .map(iou->new IOweYouDTO(iou))
                    .collect(Collectors.toList());
    }
}
