package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserController {

    public Button newConfigButton;
    public Button loginButton;
    public TableView userTableView;

    public void createNewConfig(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent configParent = FXMLLoader.load(getClass().getResource("Configuration.fxml"));
        Scene configScene = new Scene(configParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(configScene);
        window.show();
    }

    public void openLoginScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login Page.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void viewConfigurations() {
        //Show all configurations made by the user in tableView
    }

}
