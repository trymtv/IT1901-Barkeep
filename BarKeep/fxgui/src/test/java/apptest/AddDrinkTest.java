package apptest;

import barkeep.AddDrinkController;
import barkeep.IOweYou;
import barkeep.User;
import database.Database;
import database.FriendRepository;
import database.IOweYouRepository;
import database.UserRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class AddDrinkTest extends ApplicationTest {

    private AddDrinkController controller;

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, ClassNotFoundException {
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        setOwner(UserRepository.get("testuser1"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddDrink.fxml"));
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
            FriendRepository.store(UserRepository.get("testuser1").getId(), UserRepository.get("testuser2").getId());
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
    public void testControllerandOverviewButton() {
        assertNotNull(this.controller);
        clickOn("#showOverview");
    }

    @Test
    public void testAddDrinkandLogoutButton() throws SQLException, ClassNotFoundException {
        List<IOweYou> oldList = IOweYouRepository.getByOwner(getOwner());
        clickOn("#choiceBoxFriends");
        type(KeyCode.DOWN, KeyCode.ENTER);
        clickOn("#choiceBoxDrinks");
        type(KeyCode.ENTER);
        clickOn("#addDrink");
        assertNotEquals(oldList, IOweYouRepository.getByOwner(getOwner()));
        clickOn("#logout");
        assertNull(getOwner());
    }

    @Test
    public void testFriendsButton(){
        clickOn("#findFriendsButton");
    }


}
