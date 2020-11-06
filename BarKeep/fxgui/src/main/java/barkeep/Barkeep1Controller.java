package barkeep;

import database.DrinkRepository;
import database.FriendRepository;
import database.IOweYouRepository;
import database.UserRepository;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static barkeep.LoginController.getOwner;
import static barkeep.LoginController.setOwner;

public class Barkeep1Controller implements Initializable {

    @FXML
    private ChoiceBox<User> choiceBoxFriends;
    @FXML
    private ChoiceBox<Drink> choiceBoxDrinks;
    @FXML
    private Label feedback;
    @FXML
    Button addDrink;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateChoiceBoxDrinks();
        populateChoiceBoxFriends();
        disableButton();
    }

    public void populateChoiceBoxDrinks() {
        ObservableList<Drink> choiceBoxListDrinks = FXCollections.observableArrayList();
        choiceBoxListDrinks.removeAll();
        try {
            choiceBoxListDrinks.addAll(DrinkRepository.getAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        choiceBoxDrinks.getItems().addAll(choiceBoxListDrinks);
    }

    public void populateChoiceBoxFriends() {
        ObservableList<User> choiceBoxListFriends = FXCollections.observableArrayList();
        choiceBoxListFriends.removeAll();
        List<User> friendList = new ArrayList<>();
        try {
            List<Integer> friendIDs = FriendRepository.get(getOwner().getId());
            if(friendIDs == null){
                return;
            }
            friendIDs.forEach(id -> {
                try {
                    friendList.add(UserRepository.get(id));
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            });
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        choiceBoxListFriends.addAll(friendList);
        choiceBoxFriends.getItems().addAll(choiceBoxListFriends);
    }

    public void handleGetOverview(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Barkeep2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void disableButton() {
        addDrink.disableProperty().bind(
                choiceBoxFriends.valueProperty().isNull()
                        .or(choiceBoxDrinks.valueProperty().isNull()));
    }

    public void handleAddDrink() {
        Drink drink = choiceBoxDrinks.getValue();
        User user = choiceBoxFriends.getValue();
        System.out.println(user);
        try {
            IOweYouRepository.store(new IOweYou(getOwner(), user, drink));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        feedback.setText("Drink was added");
    }

    public void handleLogout(ActionEvent event) throws IOException {
        setOwner(null);
        Parent parent = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void handleFindFriends(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FriendRegistration.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
