package controller;

import api.controller.DrinkController;
import barkeep.Drink;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.DrinkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DrinkController.class)
@SpringJUnitConfig(classes = {DrinkController.class, TestSecurityConfiguration.class})
public class DrinkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DrinkService service;

    @Test
    @WithMockUser
    public void whenDrink_shouldReturnMessage() throws Exception {
        mockMvc.perform(get("/drink/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("DrinkController")));
    }

    @Test
    @WithMockUser
    public void whenGetAll_shouldReturnDrinks() throws Exception {
        Drink d1 = new Drink("drink1", 123);
        List<Drink> drinks = Arrays.asList(d1);
        given(service.list()).willReturn(drinks);
        mockMvc.perform(get("/drink/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(drinks.size())))
                .andExpect(jsonPath("$[0].name", is(drinks.get(0).getName())));
    }

    @Test
    @WithMockUser
    public void whenGetById_shouldReturnDrink() throws Exception {
        Drink drink = new Drink("drink1", 1234);
        drink.setId(1);
        given(service.get(1)).willReturn(drink);
        mockMvc.perform(get("/drink/1"))
                .andExpect(jsonPath("$.name", is(drink.getName())))
                .andExpect(jsonPath("$.value", is(drink.getValue())));

    }

    @Test
    @WithMockUser
    public void whenDrinkByName_shouldReturnDrink() throws Exception {
        Drink drink = new Drink("drink1", 1234);
        drink.setId(1);
        given(service.getByName("drink")).willReturn(drink);
        mockMvc.perform(get("/drink/name/drink"))
                .andExpect(jsonPath("$.name", is(drink.getName())))
                .andExpect(jsonPath("$.value", is(drink.getValue())));
    }

    @Test
    @WithMockUser
    public void whenSearchByName_shouldReturnDrink() throws Exception {
        Drink drink = new Drink("drink1", 1234);
        drink.setId(1);
        given(service.searchByName("dr")).willReturn(Arrays.asList(drink));
        mockMvc.perform(get("/drink/search/dr"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(drink.getName())))
                .andExpect(jsonPath("$[0].value", is(drink.getValue())));
    }

    @Test
    @WithMockUser(roles = "")
    public void whenUpdate_shouldReturnUpdatedDrink() throws Exception {
        Drink drink = new Drink("drinkToUpdate", 9874);
        drink.setId(1);
        given(service.save(any(Drink.class))).willAnswer(i -> i.getArgument(0));
        String data = new ObjectMapper().writeValueAsString(drink);
        mockMvc.perform(put("/drink/1/").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(drink.getName())))
                .andExpect(jsonPath("$.value", is(drink.getValue())));
    }

    @Test
    @WithMockUser
    public void whenDelete_shouldNotReturn() throws Exception {
        Drink drink = new Drink("drink1", 1234);
        drink.setId(1);
        given(service.get(1)).willReturn(drink).willReturn(null);
        mockMvc.perform(get("/drink/1"))
                .andExpect(jsonPath("$.name", is(drink.getName())))
                .andExpect(jsonPath("$.value", is(drink.getValue())));
        mockMvc.perform(delete("/drink/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/drink/1"))
                .andExpect(jsonPath("$").doesNotExist());
    }
}
