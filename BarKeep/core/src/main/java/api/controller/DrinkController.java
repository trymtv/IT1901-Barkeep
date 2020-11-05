package api.controller;

import barkeep.Drink;
import database.DrinkRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DrinkController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @RequestMapping("/drinks")
    public List<Drink> drinks() {
        try {
            return DrinkRepository.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Drink>(List.of(new Drink("lol", 123)));
    }
    @RequestMapping("/drink/{id}")
    public Drink drink(@PathVariable int id) {
        try {
            return DrinkRepository.get(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Drink("lol", 123);
    }
}
