package apptest;

import static barkeep.App.getOwner;
import static barkeep.App.setOwner;
import static org.junit.jupiter.api.Assertions.*;

import barkeep.OverviewController;
import barkeep.IOweYou;
import barkeep.User;
import database.*;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import repositories.*;

public class OverviewTest extends ApplicationTest {

    private OverviewController controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        HttpManager.setContext("MrsTest", "besttest");
        setOwner(UserRepository.get("MrsTest"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Overview.fxml"));
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
        HttpManager.setContext("MrsTest", "besttest");
        FriendRepository.store(UserRepository.get("MrsTest").getId(), UserRepository.get("MrTest").getId());
        IOweYouRepository.store(new IOweYou(getOwner(), UserRepository.get("MrTest"), DrinkRepository.get(1)));
    }

    @Test
    public void testControllerandBackButton() {
        assertNotNull(this.controller);
        clickOn("#back");
    }

    @Test
    public void testTableandLogout(){
        TableView<IOweYou> table = lookup("#table").query();
        assertEquals(IOweYouRepository.getByOwner(getOwner()).toString(), table.getItems().toString());
        clickOn("#logout");
        assertNull(getOwner());
    }


    private void login(){
        clickOn("#username");
        write("testuser1");
        clickOn("#loginButton");
    }

}
