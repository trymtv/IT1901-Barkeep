package apptest;

import static barkeep.App.getOwner;
import static barkeep.App.setOwner;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import barkeep.LoginController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


public class LoginTest extends ApplicationTest {

  private LoginController controller;

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
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
    clickOn("#createuserbutton");
  }

  @Test
  public void testLogin() {
    clickOn("#username");
    write("per");
    clickOn("#password");
    write("passord123");
    clickOn("#loginButton");
  }

  @Test
  public void testWrongUsername() {
    clickOn("#username");
    write("aøsdlfkjeorigwog");
    clickOn("#loginButton");
    assertNull(getOwner());
  }

}


