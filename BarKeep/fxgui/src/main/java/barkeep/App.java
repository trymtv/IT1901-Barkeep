package barkeep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/Barkeep1.fxml"));
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}

	public static boolean functionToTest(){
		if (true){
			System.out.println("It was true");
			return true;
		}else{
			System.out.println("this should not be covered.");
			return false;
		}
	}
}
