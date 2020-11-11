package barkeep;

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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static barkeep.LoginController.getOwner;

public class FriendRegistrationController implements Initializable {

    @FXML
    public ListView<User> userList;
    @FXML
    public Button addFriendButton;
    @FXML
    public ListView<User> userList2;
    @FXML
    public Button removeFriendButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateAddFriendsList();
        updateRemoveFriendsList();
        disableAddFriendButton();
        disableRemoveFriendButton();
    }

    public List<User> getfriendslist(){
        List<User> friendList = new ArrayList<>();
        try {
            List<Integer> friendIDs = FriendRepository.get(UserRepository.get(getOwner().getUsername()).getId());
            if (friendIDs == null){
                return null;
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
        return friendList;
    }

    public void updateAddFriendsList(){
        List<User> friendList = getfriendslist();
        ObservableList<User> observableUsers = FXCollections.observableArrayList();
        observableUsers.removeAll();
        try {
            observableUsers.addAll(UserRepository.getAllExcept(getOwner().getUsername()));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        if (friendList != null){
            List<String> stringFriends = new ArrayList<>();
            friendList.forEach(user -> stringFriends.add(user.toString()));
            ObservableList<User> observableUsers2 = FXCollections.observableArrayList();
            observableUsers2.addAll(observableUsers.stream().filter(user ->
                    !stringFriends.contains(user.toString())).collect(Collectors.toList()));
            userList.getItems().addAll(observableUsers2);
        }
        else{
            userList.getItems().addAll(observableUsers);
        }
    }

    public void updateRemoveFriendsList(){
        ObservableList<User> observableFriends = FXCollections.observableArrayList();
        observableFriends.removeAll();
        List<User> friendList = new ArrayList<>();
        try {
            List<Integer> friendIDs = FriendRepository.get(getOwner().getId());
            if (friendIDs == null){
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
        observableFriends.addAll(friendList);
        userList2.getItems().addAll(observableFriends);

    }

    public void handleBack(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Barkeep1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void disableAddFriendButton() {
        addFriendButton.disableProperty().bind(userList.getSelectionModel().selectedItemProperty().isNull());
    }

    public void disableRemoveFriendButton() {
        removeFriendButton.disableProperty().bind(userList2.getSelectionModel().selectedItemProperty().isNull());
    }

    public void handleAddFriend(){
        User newFriend = userList.getSelectionModel().getSelectedItem();
        try {
            FriendRepository.store(UserRepository.get(getOwner().getId()).getId(),
                    UserRepository.get(newFriend.getId()).getId());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        userList.getSelectionModel().clearSelection();
        userList.getItems().clear();
        userList2.getItems().clear();
        updateAddFriendsList();
        updateRemoveFriendsList();
    }

    public void handleRemoveFriend(){
        User oldFriend = userList2.getSelectionModel().getSelectedItem();
        try {
            FriendRepository.delete(UserRepository.get(getOwner().getId()).getId(),
                    UserRepository.get(oldFriend.getId()).getId());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        userList2.getSelectionModel().clearSelection();
        userList.getItems().clear();
        userList2.getItems().clear();
        updateAddFriendsList();
        updateRemoveFriendsList();
    }
}
