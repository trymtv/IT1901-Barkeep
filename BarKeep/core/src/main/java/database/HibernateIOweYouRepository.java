package database;

import barkeep.IOweYou;
import barkeep.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HibernateIOweYouRepository extends JpaRepository<IOweYou, Integer> {
    List<IOweYou> findAllByOwner(User owner);

    List<IOweYou> findAllByUser(User friend);
}
