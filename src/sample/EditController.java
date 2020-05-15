package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EditController implements Initializable {
    public ChoiceBox<String> compTypeChoiceBox;
    public ChoiceBox<String> compChoiceBox;
    public TextField compPriceField;
    public TextField compNameField;
    public Button cancelButton;
    public Label compErrorLabel;
    public String id;

    @FXML
    public void initialize(URL url, ResourceBundle rb){
        ComponentManager comp = new ComponentManager();
        try {
            List<Component> compList = comp.loadList();
            List<String> compString = new ArrayList<>();
            for (Component component : compList){
                compString.add(component.getId() + ", " + component.getName());
            }
            compChoiceBox.setItems(FXCollections.observableArrayList(compString));
        } catch (Exception e) {
            e.printStackTrace();
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
    public void editComponent(ActionEvent actionEvent) throws IOException {
        //Save input values to variables
        String componentType = compTypeChoiceBox.getValue();
        String componentName = compNameField.getText();
        String componentPrice = compPriceField.getText();

        String[] idString = compChoiceBox.getValue().split(",");
        String id = idString[0];

        //ERROR HANDLING: make sure price string is a valid double
        boolean priceCheck;
        if (componentPrice.equals("")){
            priceCheck = true;
        }else {
            try {
                Double.parseDouble(componentPrice);
                priceCheck = true;
            } catch (NumberFormatException e) {
                System.out.println(e);
                priceCheck = false;
            }
        }

        if(priceCheck) {
            //ERROR HANDLING: make sure all strings match the regex
            String componentTypeRegex = "[a-zA-Z '\\-]{3,20}";
            String componentNameRegex = "[\"a-zA-Z 0-9()'\\-]{3,40}";
            String componentPriceRegex = "[0-9.]{1,6}";
            if((Pattern.matches(componentTypeRegex, componentType) || componentPrice.equals("")) && (Pattern.matches(componentNameRegex, componentName) || componentPrice.equals("")) && (Pattern.matches(componentPriceRegex, componentPrice) || componentPrice.equals(""))) {

                //If everything is OK, create component
                ComponentManager componentManager = new ComponentManager();
                componentManager.edit_component(componentName, componentType, componentPrice, id);
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
