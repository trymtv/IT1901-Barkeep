package barkeep;

import database.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    private static User owner;

    @FXML
    PasswordField password;
    @FXML
    TextField username;
    @FXML
    Label feedback;

    public static void setOwner(User newOwner) {
        owner = newOwner;
    }

    public static User getOwner(){
        return owner;
    }


    public void handleLogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (checkUserName()) {
            setOwner(UserRepository.get(username.getText()));
            Parent parent = FXMLLoader.load(getClass().getResource("/AddDrink.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else{
            feedback.setText("Invalid username or password");
        }
    }

    public void handleCreateUser(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Registration.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

/*    public boolean checkPassword(){

    }*/

    public boolean checkUserName() throws SQLException, ClassNotFoundException {
        User user = UserRepository.get(username.getText());
        return user != null;
    }
}
