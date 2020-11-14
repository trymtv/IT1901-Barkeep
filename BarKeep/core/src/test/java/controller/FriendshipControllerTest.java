package controller;

import api.controller.FriendshipController;
import barkeep.Friendship;
import barkeep.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.FriendshipService;
import database.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendshipController.class)
@SpringJUnitConfig(classes = FriendshipController.class)
public class FriendshipControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FriendshipService service;
    @MockBean
    private UserService userService;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        user1 = new User("Testuser1", "d", "h@h.com");
        user2 = new User("Testuser2", "d", "h2@h.com");
        user3 = new User("Testuser3", "d", "h2@h.com");
        Friendship fs1 = new Friendship(user1, user2);
        Friendship fs2 = new Friendship(user3, user1);
        given(userService.get(1)).willReturn(user1);
        given(service.getFriends(user1)).willReturn(Arrays.asList(user2, user3));
    }

    @Test
    public void whenGetFriends_thenReturnUsers() throws Exception {
        mockMvc.perform(get("/friendship/1"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is(user2.getUsername())))
                .andExpect(jsonPath("$[1].username", is(user3.getUsername())));
    }

    @Test
    public void whenAddFriendship_thenReturnFriendship() throws Exception {
        Friendship frship = new Friendship(user3, user2);
        String data = new ObjectMapper().writeValueAsString(frship);
        given(service.add(any(Friendship.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(post("/friendship/").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user1.username", is(user3.getUsername())))
                .andExpect(jsonPath("$.user2.username", is(user2.getUsername())));
    }

    @Test
    public void whenRemoveFriendship_thenReturnOK() throws Exception {
        Friendship frship = new Friendship(user3, user2);
        String data = new ObjectMapper().writeValueAsString(frship);
        mockMvc.perform(delete("/friendship/").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
