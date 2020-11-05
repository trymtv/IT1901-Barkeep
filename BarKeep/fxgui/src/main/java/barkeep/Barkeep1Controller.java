package barkeep;

import database.DrinkRepository;
import database.FriendRepository;
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

public class Barkeep1Controller implements Initializable {

    @FXML
    private ChoiceBox<User> choiceBoxFriends;
    @FXML
    private ChoiceBox<Drink> choiceBoxDrinks;
    @FXML
    private Label feedback;
    @FXML
    Button addDrink;

    private static User owner;

    static {
        try {
            owner = UserRepository.get(1);
            owner.setIOweYouList(new ArrayList<>());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateChoiceBoxDrinks();
        populateChoiceBoxFriends();
        disableButton();
    }

    public static User getOwner() {
        return owner;
    }

    public static void setOwner(User newOwner) {
        owner = newOwner;
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

    public void populateChoiceBoxFriends(){
        ObservableList<User> choiceBoxListFriends = FXCollections.observableArrayList();
        choiceBoxListFriends.removeAll();
        List<User> friendList = new ArrayList<>();
        try {
            List<Integer> friendIDs = FriendRepository.get(getOwner().getId());
            assert friendIDs != null;
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
        System.out.println(getClass().getResource("/Barkeep2.fxml"));
        Parent parent = FXMLLoader.load(getClass().getResource("/Barkeep2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void disableButton(){
        addDrink.disableProperty().bind(
                choiceBoxFriends.valueProperty().isNull()
                .or(choiceBoxDrinks.valueProperty().isNull()));
    }

    public void handleAddDrink() {
        Drink drink = choiceBoxDrinks.getValue();
        User user = choiceBoxFriends.getValue();
        getOwner().addIOweYou(new IOweYou(getOwner(), user, drink));
        feedback.setText("Drink was added");

    }
}
