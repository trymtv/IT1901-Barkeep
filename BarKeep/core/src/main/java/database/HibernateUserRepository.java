package database;

import barkeep.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HibernateUserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsernameIgnoreCaseContaining(String username);

}
