package barkeep;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import database.FriendRepository;
import database.UserRepository;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static barkeep.Barkeep1Controller.getOwner;

public class Barkeep2Controller implements Initializable {

    @FXML
    private TableView<IOU> table;
    @FXML
    private TableColumn<IOU, String> user;
    @FXML
    private TableColumn<IOU, String> drink;
    @FXML
    private TableColumn<IOU, LocalDateTime> time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    public void updateTable(){
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        drink.setCellValueFactory(new PropertyValueFactory<>("drink"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        ObservableList<IOU> observableList = FXCollections.observableArrayList();
        observableList.removeAll();
        observableList.addAll(getOwner().getIOUList());
        table.setItems(observableList);
    }

    public void handleBack(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Barkeep1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    }

