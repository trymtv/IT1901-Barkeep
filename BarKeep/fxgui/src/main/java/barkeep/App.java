package barkeep;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

  private static User owner;

  public static User getOwner() {
    return owner;
  }

  public static void setOwner(User newOwner) {
    owner = newOwner;
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/Login.fxml"));
    primaryStage.setScene(new Scene(parent));
    primaryStage.show();
  }

  @Override
  public void stop() {
    setOwner(null);
  }

}
