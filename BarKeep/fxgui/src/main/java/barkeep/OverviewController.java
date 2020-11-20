package barkeep;

import static barkeep.App.getOwner;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repositories.IOweYouRepository;


public class OverviewController implements Initializable {

  @FXML
  private Button deleteIOU;
  @FXML
  private TableView<IOweYou> table;
  @FXML
  private TableColumn<IOweYou, String> user;
  @FXML
  private TableColumn<IOweYou, String> drink;
  @FXML
  private TableColumn<IOweYou, LocalDateTime> time;
  @FXML
  private TableView<IOweYou> table2;
  @FXML
  private TableColumn<IOweYou, String> user2;
  @FXML
  private TableColumn<IOweYou, String> drink2;
  @FXML
  private TableColumn<IOweYou, LocalDateTime> time2;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    updateTable();
    updateTable2();
    disableDeleteButton();
  }

  private void updateTable() {
    user.setCellValueFactory(new PropertyValueFactory<>("user"));
    drink.setCellValueFactory(new PropertyValueFactory<>("drink"));
    time.setCellValueFactory(new PropertyValueFactory<>("time"));
    ObservableList<IOweYou> observableList = FXCollections.observableArrayList();
    observableList.removeAll();
    observableList.addAll(IOweYouRepository.getByOwner(getOwner()));
    table.setItems(observableList);
  }

  private void updateTable2() {
    user2.setCellValueFactory(new PropertyValueFactory<>("owner"));
    drink2.setCellValueFactory(new PropertyValueFactory<>("drink"));
    time2.setCellValueFactory(new PropertyValueFactory<>("time"));
    ObservableList<IOweYou> observableList = FXCollections.observableArrayList();
    observableList.removeAll();
    observableList.addAll(IOweYouRepository.getByFriend(getOwner()));
    table2.setItems(observableList);
  }

  /**
   * Switches scene to AddDrink.
   *
   * @throws IOException fxml document for AddDrink is not found.
   */
  public void handleBack(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/AddDrink.fxml"));
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

  public void handleDeleteIou() {
    IOweYouRepository.delete(table.getSelectionModel().getSelectedItem());
    updateTable();
  }

  public void disableDeleteButton() {
    deleteIOU.disableProperty()
        .bind(table.getSelectionModel().selectedItemProperty().isNull());
  }
}