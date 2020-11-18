package barkeep;

import static barkeep.App.getOwner;
import static barkeep.App.setOwner;

import database.FriendRepository;
import database.UserRepository;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class FriendRegistrationController implements Initializable {

    @FXML
    TextField searchFriends;
    @FXML
    TextField searchUsers;
    @FXML
    ListView<User> userList;
    @FXML
    ListView<User> userList2;
    @FXML
    Button addFriendButton;
    @FXML
    Button removeFriendButton;

    List<User> friendsList;
    List<User> allUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.friendsList = getFriendList();
            this.allUsers = UserRepository.getAllExcept(getOwner().getUsername());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        updateAddFriendsList();
        updateFriendsList();
        disableAddFriendButton();
        disableRemoveFriendButton();
    }

    public static List<User> getFriendList() {  // Skal erstattes av metode i Friendrepository
        List<User> friendsList = new ArrayList<>();
        List<Integer> friendIDs = null;
        try {
            friendIDs = FriendRepository.get(getOwner().getUsername());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        if (friendIDs != null) {
            friendIDs.forEach(id -> {
                try {
                    friendsList.add(UserRepository.get(id));
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            });
        }
        return friendsList;
    }

    private void updateAddFriendsList() {
        List<String> friendsAsString = this.friendsList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        ObservableList<User> findFriends = FXCollections.observableArrayList();
        findFriends.removeAll();
        findFriends.addAll(this.allUsers.stream()
                .filter(user -> !friendsAsString.contains(user.getUsername()))
                .collect(Collectors.toList()));
        userList.getItems().addAll(findFriends);
    }

    private void updateFriendsList() {
        ObservableList<User> friendList = FXCollections.observableArrayList();
        friendList.removeAll();
        friendList.addAll(this.friendsList);
        userList2.getItems().addAll(friendList);
    }

    /**
     * Adds and stores the friend, then updates the friend lists.
     */
    public void handleAddFriend() {
        User newFriend = userList.getSelectionModel().getSelectedItem();
        this.friendsList.add(newFriend);
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
        updateFriendsList();
        searchUsers.clear();
    }

    /**
     * Deletes the friend and updates the friend lists.
     */
    public void handleRemoveFriend() {
        User oldFriend = userList2.getSelectionModel().getSelectedItem();
        this.friendsList.remove(oldFriend);
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
        updateFriendsList();
        searchFriends.clear();
    }

    /**
     * Switches scene to AddDrink.
     *
     *@throws IOException fxml document for AddDrink is not found.
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
        setOwner(null);
        Parent parent = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Filter users based on input.
     */
    public void filterFindFriends() {
        userList.getItems().clear();
        ObservableList<User> obsList = FXCollections.observableArrayList();
        obsList.removeAll();
        List<String> friendListString = this.friendsList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        obsList.addAll(this.allUsers.stream()
                .filter(user -> !friendListString.contains(user.getUsername()))
                .filter(user -> user.getUsername().toLowerCase()
                        .contains(searchUsers.getText().toLowerCase()))
                .collect(Collectors.toList()));
        userList.getItems().addAll(obsList);
    }

    /**
     * Filter friends based on input.
     */
    public void filterMyFriends() {
        userList2.getItems().clear();
        ObservableList<User> obsList = FXCollections.observableArrayList();
        obsList.removeAll();
        obsList.addAll(this.friendsList.stream()
                .filter(user -> user.getUsername().toLowerCase()
                .contains(searchFriends.getText().toLowerCase()))
                .collect(Collectors.toList()));
        userList2.getItems().addAll(obsList);
    }

    private void disableAddFriendButton() {
        addFriendButton.disableProperty()
                .bind(userList.getSelectionModel().selectedItemProperty().isNull());
    }

    private void disableRemoveFriendButton() {
        removeFriendButton.disableProperty()
                .bind(userList2.getSelectionModel().selectedItemProperty().isNull());
    }
}

