package database;

import barkeep.User;
import barkeep.UserDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    HibernateUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> searchByUsername(String name) {
        return userRepository.findByUsernameIgnoreCaseContaining(name);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User get(UserDTO userDTO) {
        return get(userDTO.getId());
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO convertToDTO(User user) {
        return new UserDTO(user);
    }

    public List<UserDTO> convertListToDTOs(List<User> userList) {
        return userList.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean isSameUser(User user, User loggedInUser) {
        return user.getId() == loggedInUser.getId();
    }
}
