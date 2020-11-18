package apptest;

import static barkeep.App.getOwner;
import static barkeep.App.setOwner;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import barkeep.LoginController;
import barkeep.User;
import database.Database;
import database.UserRepository;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class LoginTest extends ApplicationTest {

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
        try {
            UserRepository.store(testuser1);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterAll
    public static void restoreDB(){
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        try {
            UserRepository.delete(UserRepository.get("testuser1"));
            //UserRepository.delete(UserRepository.get("testuser2"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testController() {
        assertNotNull(this.controller);
        clickOn("#createuserbutton");
    }

    @Test
    public void testLogin(){
        clickOn("#username");
        write("testuser1");
        clickOn("#loginButton");

    }

    @Test
    public void testWrongUsername(){
        clickOn("#username");
        write("&%$%&%()&/%");
        clickOn("#loginButton");
        assertNull(getOwner());
    }

}


