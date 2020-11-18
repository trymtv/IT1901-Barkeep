package apptest;

import static barkeep.App.setOwner;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import barkeep.RegistrationController;
import database.Database;
import database.UserRepository;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class RegistrationTest extends ApplicationTest {

    private RegistrationController controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Database.setDbUrl("jdbc:h2:../core/src/test/resources/testdb");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.controller = loader.getController();
    }

    @Override
    public void stop() {
        setOwner(null);
    }

    @Test
    public void testController() {
        assertNotNull(this.controller);
        clickOn("#backButtonReg");
    }

    @Test
    public void testCreateUser() {
        clickOn("#nameField");
        write("testuser1");
        clickOn("#emailField");
        write("testuser@barkeep.com");
        clickOn("#passwordField");
        write("Barkeep4life<3");
        clickOn("#register");
        try {
            assertNotNull(UserRepository.get("testuser1"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        resetDB();
    }

    public void resetDB() {
        try {
            UserRepository.delete(UserRepository.get("testuser1"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
