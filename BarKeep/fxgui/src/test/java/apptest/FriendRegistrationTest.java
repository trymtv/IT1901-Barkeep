package apptest;

import static barkeep.App.getOwner;
import static barkeep.App.setOwner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import barkeep.FriendRegistrationController;
import barkeep.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import repositories.FriendRepository;
import repositories.HttpManager;
import repositories.UserRepository;

public class FriendRegistrationTest extends ApplicationTest {

  private FriendRegistrationController controller;

  @Override
  public void start(Stage primaryStage) throws IOException {
    HttpManager.setContext("MrsTest", "besttest");
    setOwner(UserRepository.get("MrsTest"));
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FriendRegistration.fxml"));
    Parent parent = loader.load();
    Scene scene = new Scene(parent);
    primaryStage.setScene(scene);
    primaryStage.show();
    this.controller = loader.getController();
  }

  @Override
  public void stop() {
    setOwner(null);
  }

  @Test
  public void testControllerandBackButton() {
    assertNotNull(this.controller);
    clickOn("#backButton");
  }

  @Test
  public void testFilterAddFriendsRemoveFriendsLogout() {
    HttpManager.setContext("MrsTest", "besttest");
    List<User> oldFriendList = FriendRepository.get("MrsTest");
    clickOn("#searchUsers");
    write("per");
    ListView<User> userList = lookup("#userList").query();
    assertEquals(1, userList.getItems().size());
    assertEquals("per", userList.getItems().get(0).toString());
    clickOn("#userList");
    type(KeyCode.ENTER);
    clickOn("#addFriendButton");
    assertNotEquals(oldFriendList, FriendRepository.get("MrsTest"));
    clickOn("#removeTab");
    clickOn("#searchFriends");
    write("per");
    userList = lookup("#userList2").query();
    assertEquals(1, userList.getItems().size());
    clickOn("#userList2");
    type(KeyCode.DOWN, KeyCode.ENTER);
    clickOn("#removeFriendButton");
    assertEquals(oldFriendList.toString(), FriendRepository.get("MrsTest").toString());
    clickOn("#logoutButtonFriends");
    assertNull(getOwner());
  }
}
