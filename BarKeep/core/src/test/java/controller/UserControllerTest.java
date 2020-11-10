package controller;

import api.BarkeepApp;
import api.controller.DrinkController;
import api.controller.UserController;
import barkeep.User;
import database.HibernateDrinkRepository;
import database.HibernateUserRepository;
import database.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@SpringJUnitConfig(classes = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;
    @MockBean
    private HibernateUserRepository repository;

    @Test
    public void whenGetAll_thenReturnUsers() throws Exception {
        User u1 = new User("user1", "username123", "testpass", "user@user.com");
        User u2 = new User("user2", "username334", "testpass", "user1@user.com");
        given(service.list()).willReturn(Arrays.asList(u1,u2));
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(u1.getName())))
                .andExpect(jsonPath("$[1].name", is(u2.getName())));
    }

    @Test
    public void whenSearchByName_thenReturnUsers() throws Exception {
        User u1 = new User("user1", "username123", "testpass", "user@user.com");
        List<User> users = Arrays.asList(u1);
        given(service.searchByUsername("us")).willReturn(users);
        mockMvc.perform(get("/user/search/us"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(users.size())))
                .andExpect(jsonPath("$[0].name", is(u1.getName())))
                .andExpect(jsonPath("$[0].username", is(u1.getUsername())))
                .andExpect(jsonPath("$[0].email", is(u1.getEmail())));
    }
}
