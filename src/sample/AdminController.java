package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminController {

    public Button newComponentButton;
    public TableView adminTableView;
    public Button logOutButton;
    public ChoiceBox compChoiceBox;
    public TextField deleteSearchField;

    public void logOut(ActionEvent actionEvent) throws IOException {
        //Log out code
        //Go back to user scene
        Parent userParent = FXMLLoader.load(getClass().getResource("User Page.fxml"));
        Scene userScene = new Scene(userParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(userScene);
        window.show();
    }

    public void openCreateComponentScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent componentParent = FXMLLoader.load(getClass().getResource("Create Component.fxml"));
        Scene componentScene = new Scene(componentParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(componentScene);
        window.show();
    }

    public void viewComponents(ActionEvent actionEvent) {
        ComponentManager componentManager = new ComponentManager();
        List<Component> sortedList = componentManager.refineList("All");
        //populate list with results here
        adminTableView.getItems().clear();
        adminTableView.getItems().addAll(sortedList);
    }

    public void deleteComponent(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        ComponentManager componentManager = new ComponentManager();
        componentManager.remove_component(deleteSearchField.getText());
        deleteSearchField.clear();
        viewComponents(actionEvent);
    }

}
