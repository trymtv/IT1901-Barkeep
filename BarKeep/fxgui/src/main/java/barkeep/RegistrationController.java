package barkeep;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repositories.UserRepository;

public class RegistrationController {

  @FXML
  private TextField nameField;
  @FXML
  private TextField emailField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Label registrationFeedback;

  //Tries to store the user and returns the status code
  private int storeUser() {
    User user = new User(nameField.getText(), passwordField.getText(), emailField.getText());
    return UserRepository.store(user);
  }

  /**
   * Stores new user and switches scene to Login.
   *
   * @throws IOException fxml document for Login is not found.
   */
  public void handleRegistration(ActionEvent event) throws IOException {
    if (storeUser() != 200) {
      registrationFeedback.setText("User registration failed.");
    } else {
      registrationFeedback.setText("");
      Parent parent = FXMLLoader.load(getClass().getResource("/Login.fxml"));
      Scene scene = new Scene(parent);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
  }

  /**
   * Switches scene to Login.
   *
   * @throws IOException fxml document for Login is not found.
   */
  public void handleBack(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/Login.fxml"));
    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }
}

