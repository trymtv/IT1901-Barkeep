package barkeep;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    private User user;
    private List<Drink> supportedDrinksList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDrinks();
        populateChoiceBoxes();
    }

    public void addDrinks(){
        this.supportedDrinksList = new ArrayList<>();
        Drink beer = new Drink("Beer", 80);
        Drink wine = new Drink("Wine", 100);
        Drink shot = new Drink ("Shot", 120);
        this.supportedDrinksList.add(beer);
        this.supportedDrinksList.add(wine);
        this.supportedDrinksList.add(shot);
    }

    public void populateChoiceBoxes(){
        ObservableList<Drink> choiceBoxListDrinks = FXCollections.observableArrayList();
        ObservableList<User> choiceBoxListFriends = FXCollections.observableArrayList();
        choiceBoxListFriends.removeAll();
        choiceBoxListDrinks.removeAll();
        /*choiceBoxListFriends.addAll(Users.getfriendsList);    //Her må brukerens venner legges til for å være
        tilgjengelige for dropdown-menyen.*/
        choiceBoxListDrinks.addAll(supportedDrinksList);
        choiceBoxDrinks.getItems().addAll(choiceBoxListDrinks);
        //choiceBoxFriends.getItems().addAll(choiceBoxListFriends); Legger til venner i dropdown  menyen
    }

    public void handleGetOverview(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Barkeep2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void handleAddDrink(){
        //Legge til drinken i oversikten over hvem som skylder deg

    }




}
