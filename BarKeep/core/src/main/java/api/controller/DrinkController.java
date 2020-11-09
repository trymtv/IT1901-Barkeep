package api.controller;

import barkeep.Drink;
import database.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drink")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @GetMapping("/")
    public String index() {
        return "DrinkController";
    }

    @GetMapping("/all")
    public List<Drink> drinks() {
        return drinkService.list();
    }

    @GetMapping("/{id}")
    public Drink drinkById(@PathVariable int id) {
        return drinkService.get(id);
    }

    @PutMapping("/{id}")
    public Drink update(@PathVariable int id, @RequestBody Drink drink) {
        drink.setId(id);
        return drinkService.save(drink);
    }

    @PostMapping("/add")
    public Drink add(@RequestBody Drink drink) {
        return drinkService.save(drink);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Drink drinkToDelete = drinkService.get(id);
        drinkService.delete(drinkToDelete);
    }

    @GetMapping("/name/{name}")
    public Drink drinkByName(@PathVariable String name) {
        return drinkService.getByName(name);
    }

    @GetMapping("/search/{search}")
    public List<Drink> search(@PathVariable String search) {
        return drinkService.searchByName(search);
    }
}
