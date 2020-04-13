package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public ChoiceBox compTypeChoiceBox;
    public TextField compPriceField;
    public TextField compNameField;
    public Button newComponentButton;
    public TableView adminTableView;
    public Button logOutButton;

    public Button newConfigButton;
    public Button loginButton;
    public TableView userTableView;

    public Button saveConfigButton;
    public ChoiceBox processorChoiceBox;
    public ChoiceBox graphicsChoiceBox;
    public ChoiceBox ramChoiceBox;
    public ChoiceBox driveChoiceBox;
    public CheckBox keyboardYes;
    public CheckBox keyboardNo;
    public CheckBox mouseYes;
    public CheckBox mouseNo;
    public ChoiceBox screenChoiceBox;
    public Label processorPriceBox;
    public Label mousePriceBox;
    public Label keyboardPriceBox;
    public Label screenPriceBox;
    public Label drivePriceBox;
    public Label ramPriceBox;
    public Label graphicsPriceBox;
    public Label totalPriceBox;

    public void saveUserConfig(ActionEvent actionEvent) {
    }

    public void calculateTotalPrice(int price) {
        //Function called each time a field value is changed
        //Take current total from totalPriceBox
        //Add incoming price to total
        //Set total price into totalPriceBox
    }

    public void createNewConfig(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent part = FXMLLoader.load(getClass().getResource("Configuration.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
    }

    public void openLoginScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent part = FXMLLoader.load(getClass().getResource("Login Page.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
    }

    public void logOut(ActionEvent actionEvent) {
    }

    public void openCreateComponentScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent part = FXMLLoader.load(getClass().getResource("Create Component.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
    }

    public void createNewComponent(ActionEvent actionEvent) {
        //create new product class instance
        //get choice box and input field values
        //save to file
        //return to admin page
    }
}
