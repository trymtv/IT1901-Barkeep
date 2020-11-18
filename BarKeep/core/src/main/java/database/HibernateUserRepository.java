package database;

import barkeep.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HibernateUserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    List<User> findByUsernameIgnoreCaseContaining(String username);

}
