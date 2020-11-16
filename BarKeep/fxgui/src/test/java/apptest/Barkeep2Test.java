package apptest;

import barkeep.Barkeep1Controller;
import barkeep.Barkeep2Controller;
import barkeep.IOweYou;
import barkeep.User;
import database.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
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

public class Barkeep2Test extends ApplicationTest {

    private Barkeep2Controller controller;

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, ClassNotFoundException {
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        setOwner(UserRepository.get("testuser1"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Barkeep2.fxml"));
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
            setOwner(UserRepository.get("testuser1"));
            IOweYouRepository.store(new IOweYou(getOwner(), UserRepository.get("testuser2"), DrinkRepository.get(1)));
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
        clickOn("#back");
    }

    @Test
    public void testTableandLogout(){
        TableView<IOweYou> table = lookup("#table").query();
        try {
            assertEquals(IOweYouRepository.getByOwner(getOwner()).toString(), table.getItems().toString());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        clickOn("#logout");
        assertNull(getOwner());
    }
}
