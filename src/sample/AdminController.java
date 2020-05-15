package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public Button newComponentButton;
    public TableView adminTableView;
    public Button logOutButton;
    public ChoiceBox compChoiceBox;
    public TextField deleteSearchField;
    public Button viewCompButton;
    public Button deleteCompButton;
    public Label loadErrorLabel;
    public Button editCompButton;

    private ComponentThread task;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //On page load, show all components
        task = new ComponentThread("All");
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread th = new Thread(task);
        th.setDaemon(true);
        newComponentButton.setDisable(true);
        adminTableView.setDisable(true);
        logOutButton.setDisable(true);
        compChoiceBox.setDisable(true);
        deleteSearchField.setDisable(true);
        viewCompButton.setDisable(true);
        deleteCompButton.setDisable(true);
        editCompButton.setDisable(true);
        th.start();
    }

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

    public void openEditComponentScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent editParent = FXMLLoader.load(getClass().getResource("Edit Component.fxml"));
        Scene editScene = new Scene(editParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(editScene);
        window.show();
    }

    public void viewComponents(ActionEvent actionEvent) {
        String type = compChoiceBox.getValue().toString();
        task = new ComponentThread(type);
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread th = new Thread(task);
        th.setDaemon(true);
        newComponentButton.setDisable(true);
        adminTableView.setDisable(true);
        logOutButton.setDisable(true);
        compChoiceBox.setDisable(true);
        deleteSearchField.setDisable(true);
        viewCompButton.setDisable(true);
        deleteCompButton.setDisable(true);
        editCompButton.setDisable(true);
        th.start();
    }

    private void threadDone(WorkerStateEvent e) {
        List<Component> sortedList = task.getValue();
        //populate list with results from thread
        adminTableView.getItems().clear();
        adminTableView.getItems().addAll(sortedList);

        newComponentButton.setDisable(false);
        adminTableView.setDisable(false);
        logOutButton.setDisable(false);
        compChoiceBox.setDisable(false);
        deleteSearchField.setDisable(false);
        viewCompButton.setDisable(false);
        deleteCompButton.setDisable(false);
        editCompButton.setDisable(false);
    }

    private void threadFailed(WorkerStateEvent event) {
        var e = event.getSource().getException();
        // Print error and show error message to user
        System.out.println("ERROR: Couldnt load components into admin page: "+e.getMessage());
        loadErrorLabel.setText("An error occured when loading components!");

        newComponentButton.setDisable(false);
        adminTableView.setDisable(false);
        logOutButton.setDisable(false);
        compChoiceBox.setDisable(false);
        deleteSearchField.setDisable(false);
        viewCompButton.setDisable(false);
        deleteCompButton.setDisable(false);
        editCompButton.setDisable(false);
    }

    public void deleteComponent(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        ComponentManager componentManager = new ComponentManager();
        componentManager.remove_component(deleteSearchField.getText());
        deleteSearchField.clear();
        viewComponents(actionEvent);
    }

}
