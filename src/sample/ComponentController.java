package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ComponentController {
    public ChoiceBox<String> compTypeChoiceBox;
    public TextField compPriceField;
    public TextField compNameField;
    public Button cancelButton;
    public Label compErrorLabel;

    public void openAdminScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent adminParent = FXMLLoader.load(getClass().getResource("Admin Page.fxml"));
        Scene adminScene = new Scene(adminParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(adminScene);
        window.show();
    }

    public void createNewComponent(ActionEvent actionEvent) throws IOException {
        //ERROR HANDLING: validate input values? Display errors in compErrorLabel
        //create string from input values
        String newComponentString = System.lineSeparator()+compTypeChoiceBox.getValue()+"|"+compNameField.getText()+"|"+compPriceField.getText();
        //save to file
        Writer output;
        output = new BufferedWriter(new FileWriter("./src/sample/Admin Files/components.txt", true));
        output.append(newComponentString);
        output.close();
        //return to admin page
        openAdminScene(actionEvent);
    }

}
