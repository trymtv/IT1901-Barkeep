package database;

import barkeep.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    HibernateUserRepository userRepository;

    public List<User> searchByUsername(String name){
        return userRepository.findByUsernameIgnoreCaseContaining(name);
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
