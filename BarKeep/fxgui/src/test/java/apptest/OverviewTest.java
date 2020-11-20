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
import java.util.List;

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
        setOwner(UserRepository.get("MrsTest"));
        HttpManager.setContext("MrsTest", "besttest");
        FriendRepository.store(UserRepository.get("MrsTest").getId(), UserRepository.get("MrTest").getId());
        IOweYouRepository.store(new IOweYou(getOwner(), UserRepository.get("MrTest"), DrinkRepository.get(1)));
    }

    @AfterAll
    public static void cleanup(){
        HttpManager.setContext("MrsTest", "besttest");
        setOwner(UserRepository.get("MrsTest"));
        List<IOweYou> ious = IOweYouRepository.getByOwner(getOwner());
        IOweYouRepository.delete(ious.get(ious.size()-1));
    }

    @Test
    public void testControllerRandBackButton() {
        assertNotNull(this.controller);
        clickOn("#table");
        TableView<IOweYou> table = lookup("#table").query();
        List<IOweYou> oldList = table.getItems();
        type(KeyCode.UP);
        clickOn("#deleteIOU");
        table = lookup("#table").query();
        List<IOweYou> newList = table.getItems();
        assertNotEquals(oldList, newList);
        clickOn("#back");
        IOweYouRepository.store(new IOweYou(getOwner(), UserRepository.get("MrTest"), DrinkRepository.get(1)));
    }

    @Test
    public void testTableandLogout(){
        TableView<IOweYou> table = lookup("#table").query();
        assertEquals(IOweYouRepository.getByOwner(getOwner()).toString(), table.getItems().toString());
        clickOn("#logout");
        assertNull(getOwner());
    }

}
