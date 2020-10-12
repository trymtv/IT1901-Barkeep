package barkeep;

import database.DatabaseUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {


    TextField nameField;
    TextField usernameField;
    TextField emailField;
    TextField passwordField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleRegistration(ActionEvent event) throws IOException {
        User user = new User(nameField.getText(), usernameField.getText(), emailField.getText(), passwordField.getText());
        try {
            DatabaseUser.store(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    }

