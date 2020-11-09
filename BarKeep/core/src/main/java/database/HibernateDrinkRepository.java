package database;

import barkeep.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HibernateDrinkRepository extends JpaRepository<Drink, Integer> {
    Drink findFirstByName(String name);

    List<Drink> findByNameIgnoreCaseContaining(String name);
}