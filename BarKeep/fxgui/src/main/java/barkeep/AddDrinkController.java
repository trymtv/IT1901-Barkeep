package barkeep;

import static barkeep.App.getOwner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import repositories.DrinkRepository;
import repositories.FriendRepository;
import repositories.IOweYouRepository;


public class AddDrinkController implements Initializable {

  @FXML
  Button addDrink;
  @FXML
  private ChoiceBox<User> choiceBoxFriends;
  @FXML
  private ChoiceBox<Drink> choiceBoxDrinks;
  @FXML
  private Label feedback;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    populateChoiceBoxDrinks();
    populateChoiceBoxFriends();
    disableButton();
  }

  private void populateChoiceBoxDrinks() {
    ObservableList<Drink> choiceBoxListDrinks = FXCollections.observableArrayList();
    choiceBoxListDrinks.removeAll();
    choiceBoxListDrinks.addAll(DrinkRepository.getAll());
    choiceBoxDrinks.getItems().addAll(choiceBoxListDrinks);
  }

  private void populateChoiceBoxFriends() {
    ObservableList<User> choiceBoxListFriends = FXCollections.observableArrayList();
    choiceBoxListFriends.removeAll();
    choiceBoxListFriends.addAll(FriendRepository.get(getOwner().getId()));
    choiceBoxFriends.getItems().addAll(choiceBoxListFriends);
  }

  /**
   * Creates and stores a new IOweYou.
   */
  public void handleAddDrink() {
    Drink drink = choiceBoxDrinks.getValue();
    User user = choiceBoxFriends.getValue();
    IOweYouRepository.store(new IOweYou(getOwner(), user, drink));
    feedback.setText("Drink was added");
  }

  /**
   * Changes the scene to Overview.
   */
  public void handleGetOverview(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/Overview.fxml"));
    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Sets owner to null and switches scene to Login.
   *
   * @throws IOException fxml document for Login is not found.
   */
  public void handleLogout(ActionEvent event) throws IOException {
    LoginController.handleLogout(event);
  }

  /**
   * Switches scene to FriendRegistration.
   *
   * @throws IOException fxml document for FriendRegistration is not found.
   */
  public void handleFindFriends(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/FriendRegistration.fxml"));
    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  private void disableButton() {
    addDrink.disableProperty().bind(
        choiceBoxFriends.valueProperty().isNull()
            .or(choiceBoxDrinks.valueProperty().isNull()));
  }
}
