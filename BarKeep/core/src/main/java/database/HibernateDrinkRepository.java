package database;

import barkeep.Drink;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HibernateDrinkRepository extends JpaRepository<Drink, Integer> {
  Drink findFirstByName(String name);

  List<Drink> findByNameIgnoreCaseContaining(String name);
}