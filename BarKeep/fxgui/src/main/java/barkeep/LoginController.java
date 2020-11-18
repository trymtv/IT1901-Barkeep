package barkeep;

import static barkeep.App.setOwner;

import database.UserRepository;
import java.io.IOException;
import java.sql.SQLException;
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


public class LoginController {

    @FXML
    PasswordField password;
    @FXML
    TextField username;
    @FXML
    Label feedback;

    /**
     * Checks if username is in UserRepository and switches scene to AddDrink.
     *
     * @throws IOException fxml document for AddDrink is not found.
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public void handleLogin(ActionEvent event) throws IOException,
            SQLException, ClassNotFoundException {
        if (checkUserName()) {
            setOwner(UserRepository.get(username.getText()));
            Parent parent = FXMLLoader.load(getClass().getResource("/AddDrink.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            feedback.setText("Invalid username or password");
        }
    }

    /**
     * Switches scene to CreateUser.
     *
     * @throws IOException fxml document for Registration is not found.
     */
    public void handleCreateUser(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Registration.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private boolean checkUserName() throws SQLException, ClassNotFoundException {
        User user = UserRepository.get(username.getText());
        return user != null;
    }
}
