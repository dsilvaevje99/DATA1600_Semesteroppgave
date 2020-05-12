package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public TextField usernameField;
    public TextField passwordField;
    public Label errorMessage;
    UserManager userManager = new UserManager();

    public void openUserScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent userParent = FXMLLoader.load(getClass().getResource("User Page.fxml"));
        Scene userScene = new Scene(userParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(userScene);
        window.show();
    }

    public void verifyLogin(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        //If login = OK
        if (userManager.verify_user(usernameField.getText(),passwordField.getText(), true)){
            //Open admin scene:
            openAdminScene(actionEvent);
        }else {
            usernameField.clear();
            passwordField.clear();
            errorMessage.setVisible(true);
        }
    }

    public void openAdminScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent adminParent = FXMLLoader.load(getClass().getResource("Admin Page.fxml"));
        Scene adminScene = new Scene(adminParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(adminScene);
        window.show();
    }

}
