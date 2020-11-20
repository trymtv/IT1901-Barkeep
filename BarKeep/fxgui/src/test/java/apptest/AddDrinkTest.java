package apptest;

import static barkeep.App.getOwner;
import static barkeep.App.setOwner;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import barkeep.AddDrinkController;
import barkeep.IOweYou;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import repositories.FriendRepository;
import repositories.HttpManager;
import repositories.IOweYouRepository;
import repositories.UserRepository;

public class AddDrinkTest extends ApplicationTest {

  private AddDrinkController controller;

  @BeforeAll
  public static void setup() {
    HttpManager.setContext("MrsTest", "besttest");
    FriendRepository
        .store(UserRepository.get("MrsTest").getId(), UserRepository.get("MrTest").getId());
  }

  @AfterAll
  public static void removeIOweYou() {
    HttpManager.setContext("MrsTest", "besttest");
    setOwner(UserRepository.get("MrsTest"));
    List<IOweYou> ious = IOweYouRepository.getByOwner(getOwner());
    IOweYouRepository.delete(ious.get(ious.size() - 1));
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    HttpManager.setContext("MrsTest", "besttest");
    setOwner(UserRepository.get("MrsTest"));
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddDrink.fxml"));
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
  public void testControllerandOverviewButton() {
    assertNotNull(this.controller);
    clickOn("#showOverview");
  }

  @Test
  public void testAddDrinkandLogoutButton() {
    List<IOweYou> oldList = IOweYouRepository.getByOwner(getOwner());
    clickOn("#choiceBoxFriends");
    type(KeyCode.DOWN, KeyCode.ENTER);
    clickOn("#choiceBoxDrinks");
    type(KeyCode.ENTER);
    clickOn("#addDrink");
    assertNotEquals(oldList, IOweYouRepository.getByOwner(getOwner()));
    clickOn("#logout");
    assertNull(getOwner());
  }

  @Test
  public void testFriendsButton() {
    clickOn("#findFriendsButton");
  }
}
