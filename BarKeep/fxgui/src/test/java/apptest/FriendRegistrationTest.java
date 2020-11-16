package apptest;

import barkeep.FriendRegistrationController;
import barkeep.User;
import database.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static barkeep.LoginController.getOwner;
import static barkeep.LoginController.setOwner;
import static org.junit.jupiter.api.Assertions.*;

public class FriendRegistrationTest extends ApplicationTest {

    private FriendRegistrationController controller;

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, ClassNotFoundException {
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        setOwner(UserRepository.get("testuser1"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FriendRegistration.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.controller = loader.getController();
    }

    @Override
    public void stop(){
        setOwner(null);
    }

    @BeforeAll
    public static void setup(){
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        User testuser1 = new User(56, "testuser1");
        User testuser2 = new User(57, "testuser2");
        try {
            UserRepository.store(testuser1);
            UserRepository.store(testuser2);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterAll
    public static void restoreDB(){
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        try {
            UserRepository.delete(UserRepository.get("testuser1"));
            UserRepository.delete(UserRepository.get("testuser2"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testControllerandBackButton() {
        assertNotNull(this.controller);
        clickOn("#backButton");
    }

    @Test
    public void testFilterAddFriendsRemoveFriendsLogout(){
        try {
            List<Integer> oldFriendList = FriendRepository.get("testuser1");
            clickOn("#searchUsers");
            write("testUser2");
            ListView<User> userList = lookup("#userList").query();
            assertEquals(1, userList.getItems().size());
            assertEquals("testuser2", userList.getItems().get(0).toString());
            clickOn("#userList");
            type(KeyCode.ENTER);
            clickOn("#addFriendButton");
            assertNotEquals(oldFriendList, FriendRepository.get("testuser1"));
            clickOn("#removeTab");
            clickOn("#searchFriends");
            write("testUser2");
            userList = lookup("#userList2").query();
            assertEquals(1,userList.getItems().size());
            assertEquals("testuser2", userList.getItems().get(0).toString());
            clickOn("#userList2");
            type(KeyCode.DOWN, KeyCode.ENTER);
            clickOn("#removeFriendButton");
            assertEquals(oldFriendList, FriendRepository.get("testuser1"));
            clickOn("#logoutButtonFriends");
            assertNull(getOwner());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
