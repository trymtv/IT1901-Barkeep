package barkeep;

import database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static barkeep.LoginController.setOwner;

public class App extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Database.setDbUrl("jdbc:h2:../core/src/main/resources/barkeep");
		Parent parent = FXMLLoader.load(getClass().getResource("/Login.fxml"));
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}

	@Override
	public void stop(){
		setOwner(null);
	}

}
