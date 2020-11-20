package apptest;

import static barkeep.App.setOwner;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import barkeep.RegistrationController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import repositories.HttpManager;
import repositories.UserRepository;

public class RegistrationTest extends ApplicationTest {

  private RegistrationController controller;

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration.fxml"));
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
  public void testController() {
    assertNotNull(this.controller);
    clickOn("#backButtonReg");
  }

  @Test
  public void testCreateUser() {
    clickOn("#nameField");
    write("testuser1");
    clickOn("#emailField");
    write("testuser@barkeep.com");
    clickOn("#passwordField");
    write("Barkeep4life<3");
    clickOn("#register");
    HttpManager.setContext("MrsTest", "besttest");
    assertNotNull(UserRepository.get("testuser1"));
  }

}
