package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class UserController implements Initializable {

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

    public void viewConfigurations() throws FileNotFoundException {
        List<Configuration> configurationList = new ArrayList<>();
        File confText = new File("./src/sample/User Files/configurations.txt");
        Scanner sc = new Scanner(confText);

        while (sc.hasNextLine()){
            try {
                String[] line = sc.nextLine().split(",");

                configurationList.add(new Configuration(line[0],line[1],line[2],line[3],line[4],line[5],line[6],line[7], line [8]));
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        userTableView.getItems().clear();
        userTableView.getItems().addAll(configurationList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            viewConfigurations();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
