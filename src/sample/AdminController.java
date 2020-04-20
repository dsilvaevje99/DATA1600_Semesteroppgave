package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    public Button newComponentButton;
    public TableView adminTableView;
    public Button logOutButton;

    public void logOut(ActionEvent actionEvent) throws IOException {
        //Log out code
        //Go back to user scene
        Parent userParent = FXMLLoader.load(getClass().getResource("User Page.fxml"));
        Scene loginScene = new Scene(userParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void openCreateComponentScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent componentParent = FXMLLoader.load(getClass().getResource("Create Component.fxml"));
        Scene loginScene = new Scene(componentParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void viewComponents() {
        //Show all components in tableView through it's own thread
    }

}
