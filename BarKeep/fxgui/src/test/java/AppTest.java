import barkeep.LoginController;
import barkeep.User;
import database.*;
import barkeep.IOweYou;
import database.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.sql.SQLException;


import static barkeep.LoginController.getOwner;
import static barkeep.LoginController.setOwner;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest extends ApplicationTest {

    private LoginController controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
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
    public static void setupDB(){
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
    public void testController() {
        assertNotNull(this.controller);
    }

    @Test
    public void testLoginLogout(){
        login();
        assertEquals("testuser1", getOwner().toString());
        clickOn("#logout");
        assertNull(getOwner());

    }

    @Test
    public void testWrongUsername(){
        clickOn("#username");
        write("&%$%&%()&/%");
        clickOn("#loginButton");
        assertNull(getOwner());
    }

    @Test public void testSceneSwitch(){
        login();
        clickOn("#showOverview");
        clickOn("#back");
    }

    @Test
    public void testAddDrink(){
        login();
        clickOn("#choiceBoxFriends");
        type(KeyCode.DOWN, KeyCode.ENTER);
        clickOn("#choiceBoxDrinks");
        type(KeyCode.ENTER);
        clickOn("#addDrink");
        clickOn("#showOverview");
        TableView<IOweYou> table = lookup("#table").query();
        try {
            assertEquals(IOweYouRepository.getByOwner(getOwner()).toString(), table.getItems().toString());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public void login(){
        clickOn("#username");
        write("testuser1");
        clickOn("#loginButton");
    }

}
