package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public void openUserScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent userParent = FXMLLoader.load(getClass().getResource("User Page.fxml"));
        Scene loginScene = new Scene(userParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void verifyLogin(ActionEvent actionEvent) throws IOException {
        //If login = OK
        //Open admin scene:
        openAdminScene(actionEvent);
    }

    public void openAdminScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent adminParent = FXMLLoader.load(getClass().getResource("Admin Page.fxml"));
        Scene loginScene = new Scene(adminParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

}
