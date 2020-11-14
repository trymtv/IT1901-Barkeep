package barkeep;

import static barkeep.LoginController.getOwner;

import database.IOweYouRepository;
import database.UserRepository;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class Barkeep2Controller implements Initializable {

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
    }

    private void updateTable() {
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        drink.setCellValueFactory(new PropertyValueFactory<>("drink"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        ObservableList<IOweYou> observableList = FXCollections.observableArrayList();
        observableList.removeAll();
        try {
            observableList.addAll(IOweYouRepository.getByOwner(getOwner()));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(observableList);
    }

    private void updateTable2() {
        user2.setCellValueFactory(new PropertyValueFactory<>("owner"));
        drink2.setCellValueFactory(new PropertyValueFactory<>("drink"));
        time2.setCellValueFactory(new PropertyValueFactory<>("time"));
        ObservableList<IOweYou> observableList = FXCollections.observableArrayList();
        observableList.removeAll();
        try {
            observableList.addAll(IOweYouRepository.getByFriend(getOwner()));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        table2.setItems(observableList);
    }

    /**
     * Changes the scene to start scene.
     */
    public void handleBack(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Barkeep1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}