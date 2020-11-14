package database;

import barkeep.Drink;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {
    @Autowired
    private HibernateDrinkRepository drinkRepository;

    public List<Drink> list() {
        return drinkRepository.findAll();
    }

    public Drink get(int id) {
        return drinkRepository.findById(id).orElse(null);
    }

    public Drink getByName(String name) {
        return drinkRepository.findFirstByName(name);
    }

    public List<Drink> searchByName(String name) {
        return drinkRepository.findByNameIgnoreCaseContaining(name);
    }

    public void delete(Drink drink) {
        drinkRepository.delete(drink);
    }

    public Drink save(Drink drink) {
        return drinkRepository.save(drink);
    }
}
