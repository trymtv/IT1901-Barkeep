package controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import api.controller.IOweYouController;
import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.User;
import database.IOweYouService;
import database.UserService;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(IOweYouController.class)
@SpringJUnitConfig(classes = IOweYouController.class)
public class IOweYouControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private IOweYouService service;
  @MockBean
  private UserService userService;

  private User user1;
  private User user2;
  private User user3;
  private IOweYou iou1;
  private IOweYou iou2;

  @Before
  public void setUp() {
    Drink drink = new Drink("testdrink", 123);
    user1 = new User("Testuser1", "d", "h@h.com");
    user2 = new User("Testuser2", "d", "h2@h.com");
    user3 = new User("Testuser3", "d", "h2@h.com");
    iou1 = new IOweYou(user1, user2, drink);
    iou2 = new IOweYou(user2, user3, drink);
    given(service.convertListToDTOs(any())).willCallRealMethod();
  }

  @Test
  @WithMockUser
  public void whenUserOwes_thenReturnIOweYous() throws Exception {
    given(userService.get(2)).willReturn(user2);
    given(service.userOwes(user2)).willReturn(Arrays.asList(iou1));
    mockMvc.perform(get("/ioweyou/2/userowes"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].owner.username", is(iou1.getOwner().getUsername())))
        .andExpect(jsonPath("$[0].user.username", is(iou1.getUser().getUsername())))
        .andExpect(jsonPath("$[0].drink.name", is(iou1.getDrink().getName())))
        .andExpect(jsonPath("$[0].drink.value", is(iou1.getDrink().getValue())));
  }

  @Test
  @WithMockUser
  public void whenOwesUser_thenReturnIOweYous() throws Exception {
    given(userService.get(2)).willReturn(user2);
    given(service.owesUser(user2)).willReturn(Arrays.asList(iou2));
    mockMvc.perform(get("/ioweyou/2/owesuser"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].owner.username", is(iou2.getOwner().getUsername())))
        .andExpect(jsonPath("$[0].user.username", is(iou2.getUser().getUsername())))
        .andExpect(jsonPath("$[0].drink.name", is(iou2.getDrink().getName())))
        .andExpect(jsonPath("$[0].drink.value", is(iou2.getDrink().getValue())));
  }
}
