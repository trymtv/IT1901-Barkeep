package database;

import barkeep.User;
import barkeep.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public User get(UserDTO userDTO) {
        return get(userDTO.getId());
    }
    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO convertToDTO(User user){
        return new UserDTO(user);
    }
    public List<UserDTO> convertListToDTOs(List<User> userList){
        return userList.stream()
                    .map(UserDTO::new)
                    .collect(Collectors.toList());
    }

}
