package database;

import barkeep.IOweYou;
import barkeep.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOweYouService {
    @Autowired
    HibernateIOweYouRepository iOweYouRepository;

    public IOweYou get(int id){
        return iOweYouRepository.findById(id).orElse(null);
    }

    public List<IOweYou> owesUser(User user){
        return iOweYouRepository.findAllByOwner(user);
    }

    public List<IOweYou> userOwes(User user){
        return iOweYouRepository.findAllByUser(user);
    }

    public IOweYou add(IOweYou iOweYou) {
        return iOweYouRepository.save(iOweYou);
    }
    public void delete(IOweYou iOweYou) {
        iOweYouRepository.delete(iOweYou);
    }
}
