package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigurationController {

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

    public void openUserScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent userParent = FXMLLoader.load(getClass().getResource("User Page.fxml"));
        Scene loginScene = new Scene(userParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void keyboardYesClicked(ActionEvent actionEvent) {
    }

    public void keyboardNoClicked(ActionEvent actionEvent) {
    }

    public void mouseYesClicked(ActionEvent actionEvent) {
    }

    public void mouseNoClicked(ActionEvent actionEvent) {
    }

}
