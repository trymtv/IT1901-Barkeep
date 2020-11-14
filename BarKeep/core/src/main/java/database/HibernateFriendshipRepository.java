package database;

import barkeep.Friendship;
import barkeep.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HibernateFriendshipRepository extends JpaRepository<Friendship, Integer> {
   @Query("SELECT f FROM Friendship f WHERE (f.user1 = ?1 AND f.user2 = ?2)"
           + "OR (f.user1 = ?2 AND f.user2 = ?1)")

   public Friendship getFriendshipBetween(User user1, User user2);

   public List<Friendship> getFriendshipByUser1OrUser2(User user1, User user2);
}
