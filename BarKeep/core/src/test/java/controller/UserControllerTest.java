package controller;

import api.controller.UserController;
import barkeep.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.HibernateUserRepository;
import database.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@SpringJUnitConfig(classes = {UserController.class,TestSecurityConfiguration.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;
    @MockBean
    private HibernateUserRepository repository;

    @Before
    public void setup() {
        given(service.convertListToDTOs(any())).willCallRealMethod();
        given(service.convertToDTO(any())).willCallRealMethod();
    }

    @Test
    @WithAnonymousUser
    public void whenRegister_thenReturnUser() throws Exception {
        User u1 = new User("nameUser123", "12345689", "user@user.com");
        given(service.add(any())).willReturn(u1);
        String data = new ObjectMapper().writeValueAsString(u1);
        mockMvc.perform(post("/user/register").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(u1.getUsername())))
                .andExpect(jsonPath("$.email", is(u1.getEmail())));
    }

    @Test
    @WithMockUser
    public void whenGetById_thenReturnUser() throws Exception {
        User u1 = new User("user1", "testpass", "user@user.com");
        given(service.get(1)).willReturn(u1);
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(u1.getUsername())))
                .andExpect(jsonPath("$.email", is(u1.getEmail())));
    }

    @Test
    @WithMockUser
    public void whenGetByUsername_thenReturnUser() throws Exception {
        User u1 = new User("user1", "testpass", "user@user.com");
        given(service.getByUsername("user1")).willReturn(u1);
        mockMvc.perform(get("/user/username/user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(u1.getUsername())))
                .andExpect(jsonPath("$.email", is(u1.getEmail())));
    }
    @Test
    @WithMockUser()
    public void whenGetAll_thenReturnUsers() throws Exception {
        User u1 = new User("user1", "testpass", "user@user.com");
        User u2 = new User("user2", "testpass", "user1@user.com");
        given(service.list()).willReturn(Arrays.asList(u1, u2));
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is(u1.getUsername())))
                .andExpect(jsonPath("$[1].username", is(u2.getUsername())));
    }

    @Test
    @WithMockUser
    public void whenSearchByName_thenReturnUsers() throws Exception {
        User u1 = new User("user1", "testpass", "user@user.com");
        List<User> users = Arrays.asList(u1);
        given(service.searchByUsername("us")).willReturn(users);
        mockMvc.perform(get("/user/search/us"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(users.size())))
                .andExpect(jsonPath("$[0].username", is(u1.getUsername())))
                .andExpect(jsonPath("$[0].email", is(u1.getEmail())));
    }
}
