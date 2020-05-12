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
import java.util.regex.Pattern;

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


    /*public void createNewComponent(ActionEvent actionEvent) throws IOException {
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
    }*/

    public void createNewComponent(ActionEvent actionEvent) throws IOException {
        ComponentManager componentManager = new ComponentManager();
        componentManager.create_component(compTypeChoiceBox.getValue(), compNameField.getText(), compPriceField.getText());
        //ERROR HANDLING: validate input values? Display errors in compErrorLabel
        //return to admin page
        openAdminScene(actionEvent);

    public void createNewComponent(ActionEvent actionEvent) throws IOException {
        //Save input values to variables
        String componentType = compTypeChoiceBox.getValue();
        String componentName = compNameField.getText();
        String componentPrice = compPriceField.getText();

        //ERROR HANDLING: Make sure all fields have a value. If they don't have one, set the text of the error label and stop executing method.
        if(componentType.equals("") || componentName.equals("") || componentPrice.equals("")) {
            compErrorLabel.setText("All fields must be filled out!");
            return;
        }

        //ERROR HANDLING: make sure price string is a valid double
        boolean priceCheck;
        try {
            Double.parseDouble(componentPrice);
            priceCheck = true;
        } catch (NumberFormatException e) {
            System.out.println(e);
            priceCheck = false;
        }
        if(priceCheck) {
            //ERROR HANDLING: make sure all strings match the regex
            String componentTypeRegex = "[a-zA-Z '\\-]{3,20}";
            String componentNameRegex = "[\"a-zA-Z 0-9()'\\-]{3,40}";
            String componentPriceRegex = "[0-9.]{1,6}";
            if(Pattern.matches(componentTypeRegex, componentType) && Pattern.matches(componentNameRegex, componentName) && Pattern.matches(componentPriceRegex, componentPrice)) {

                //If everything is OK, create string from input values
                String newComponentString = System.lineSeparator()+componentType+"|"+componentName+"|"+componentPrice;
                //save to file
                Writer output;
                output = new BufferedWriter(new FileWriter("./src/sample/Admin Files/components.txt", true));
                output.append(newComponentString);
                output.close();
                //return to admin page
                openAdminScene(actionEvent);

            } else {
                compErrorLabel.setText("Invalid input value(s)!");
            }
        } else {
            compErrorLabel.setText("Price must be a valid number!");
        }
    }

}
