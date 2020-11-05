import barkeep.Barkeep1Controller;
import barkeep.IOweYou;
import database.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;

import java.io.IOException;

import static barkeep.Barkeep1Controller.getOwner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest extends ApplicationTest {

    private Barkeep1Controller controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Database.setDbUrl("jdbc:h2:../core/src/main/resources/barkeep");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Barkeep1.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.controller = loader.getController();
    }


    @Test
    public void testController() {
        assertNotNull(this.controller);
    }

    @Test public void testSceneSwitch(){
        clickOn("#showOverview");
        clickOn("#back");
    }

    @Test
    public void testAddDrink1(){
        clickOn("#choiceBoxFriends");
        type(KeyCode.ENTER);
        clickOn("#choiceBoxDrinks");
        type(KeyCode.ENTER);
        clickOn("#addDrink");
        clickOn("#showOverview");
        TableView<IOweYou> table = lookup("#table").query();
        assertEquals(getOwner().getIOweYouList(), table.getItems());
        assertThat(table, TableViewMatchers.hasNumRows(1));
    }

    @Test
    public void testAddDrink2(){
        clickOn("#choiceBoxFriends");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#choiceBoxDrinks");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#addDrink");
        clickOn("#showOverview");
        TableView<IOweYou> table = lookup("#table").query();
        assertEquals(getOwner().getIOweYouList(), table.getItems());
        assertThat(table, TableViewMatchers.hasNumRows(2));
    }
}
