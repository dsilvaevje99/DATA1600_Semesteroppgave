package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationController {

    public Button saveConfigButton;
    public ChoiceBox processorChoiceBox;
    public ChoiceBox graphicsChoiceBox;
    public ChoiceBox ramChoiceBox;
    public ChoiceBox driveChoiceBox;
    public ChoiceBox screenChoiceBox;
    public RadioButton keyboardYes;
    public RadioButton keyboardNo;
    public RadioButton mouseYes;
    public RadioButton mouseNo;
    public Label processorPriceBox;
    public Label mousePriceBox;
    public Label keyboardPriceBox;
    public Label screenPriceBox;
    public Label drivePriceBox;
    public Label ramPriceBox;
    public Label graphicsPriceBox;
    public Label totalPriceBox;
    public Label errorLabel;

    public int totalPrice = 0;
    public int keyboardClicked = 0; //0 if none clicked, 1 if yes clicked, 2 if no clicked
    public int keyboardPrice = 50;
    public String keyboardFinalChoice = "Keyboard No";
    public int mouseClicked = 0;  //0 if none clicked, 1 if yes clicked, 2 if no clicked
    public int mousePrice = 25;
    public String mouseFinalChoice = "Mouse No";
    public int lastProcessorPriceAdded = 0;
    public boolean processorChosen = false;
    public int lastGraphicsPriceAdded = 0;
    public boolean graphicsChosen = false;
    public int lastRAMPriceAdded = 0;
    public boolean ramChosen = false;
    public int lastScreenPriceAdded = 0;
    public boolean screenChosen = false;
    public int lastDrivePriceAdded = 0;
    public boolean driveChosen = false;

    @FXML
    private void initialize() {
        //ERROR HANDLING: make sure the items from the file are in the right format? Delete them if they are somehow wrong?

        List<String> processorComponents = new ArrayList<>();
        List<String> graphicsComponents = new ArrayList<>();
        List<String> ramComponents = new ArrayList<>();
        List<String> driveComponents = new ArrayList<>();
        List<String> screenComponents = new ArrayList<>();
        //Loop through each line of components.txt file
        try {
            FileReader fr = new FileReader("./src/sample/Admin Files/components.txt");
            BufferedReader br = new BufferedReader(fr);//read 'file'
            String line;
            while ((line = br.readLine()) != null) {
                //For each line, split at | and check what type component is with line[0]
                String[] lineArray = line.split("\\|");
                //Add component info to arrays sorted by component type
                if (lineArray[0].equals("Processor")) {
                    String item = lineArray[1] + ", $" + lineArray[2] + " USD";
                    processorComponents.add(item);
                }
                if (lineArray[0].equals("Hard drive")) {
                    String item = lineArray[1] + ", $" + lineArray[2] + " USD";
                    driveComponents.add(item);
                }
                if (lineArray[0].equals("Screen")) {
                    String item = lineArray[1] + ", $" + lineArray[2] + " USD";
                    screenComponents.add(item);
                }
                if (lineArray[0].equals("RAM")) {
                    String item = lineArray[1] + ", $" + lineArray[2] + " USD";
                    ramComponents.add(item);
                }
                if (lineArray[0].equals("Graphics card")) {
                    String item = lineArray[1] + ", $" + lineArray[2] + " USD";
                    graphicsComponents.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Add every component array items to corresponding choice box
        processorChoiceBox.setItems(FXCollections.observableArrayList(
                processorComponents
        ));
        driveChoiceBox.setItems(FXCollections.observableArrayList(
                driveComponents
        ));
        screenChoiceBox.setItems(FXCollections.observableArrayList(
                screenComponents
        ));
        ramChoiceBox.setItems(FXCollections.observableArrayList(
                ramComponents
        ));
        graphicsChoiceBox.setItems(FXCollections.observableArrayList(
                graphicsComponents
        ));
    }

    public void saveUserConfig(ActionEvent actionEvent) throws IOException {
        //ERROR HANDLING: validate input values? make sure everything has a selection? If something is not selected, send error message to errorLabel.
        //create string from input values if everything is OK:
        String newComponentString = System.lineSeparator()+processorChoiceBox.getValue()+"|"+graphicsChoiceBox.getValue()+"|"+ramChoiceBox.getValue()+"|"+driveChoiceBox.getValue()+"|"+screenChoiceBox.getValue()+"|"+keyboardFinalChoice+"|"+mouseFinalChoice+"|"+totalPrice;
        //save to file
        Writer output;
        output = new BufferedWriter(new FileWriter("./src/sample/User Files/configurations.txt", true));
        output.append(newComponentString);
        output.close();
        //return to admin page
        openUserScene(actionEvent);
    }

    public void calculateTotalPrice(int price) {
        int newTotal = totalPrice+price;
        totalPrice = newTotal;
        String totalString = "$"+totalPrice+" USD";
        totalPriceBox.setText(totalString);
    }

    public void openUserScene(ActionEvent actionEvent) throws IOException {
        //Open new scene:
        Parent userParent = FXMLLoader.load(getClass().getResource("User Page.fxml"));
        Scene userScene = new Scene(userParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(userScene);
        window.show();
    }

    public void keyboardSelected(ActionEvent actionEvent) {
        //Determine if yes or no was clicked
        if(keyboardYes.isSelected()) {
            keyboardFinalChoice = "Keyboard Yes";
            String priceString = "+"+keyboardPrice+" USD";
            keyboardPriceBox.setText(priceString);
            //If this is the first time the button is clicked, or no was clicked last, add to total price.
            if(keyboardClicked == 0 || keyboardClicked == 2) {
                calculateTotalPrice(keyboardPrice);
                keyboardClicked = 1;
            }
        }
        if(keyboardNo.isSelected()) {
            keyboardFinalChoice = "Keyboard No";
            String priceString = "";
            keyboardPriceBox.setText(priceString);
            //If yes was selected before no, remove price from total
            if(keyboardClicked == 1) {
                calculateTotalPrice(-keyboardPrice);
                keyboardClicked = 2;
            }
        }
    }

    public void mouseSelected(ActionEvent actionEvent) {
        //Determine if yes or no was clicked
        if(mouseYes.isSelected()) {
            mouseFinalChoice = "Mouse Yes";
            String priceString = "+"+mousePrice+" USD";
            mousePriceBox.setText(priceString);
            //If this is the first time the button is clicked, or no was clicked last, add to total price.
            if(mouseClicked == 0 || mouseClicked == 2) {
                calculateTotalPrice(mousePrice);
                mouseClicked = 1;
            }
        }
        if(mouseNo.isSelected()) {
            mouseFinalChoice = "Mouse No";
            String priceString = "";
            mousePriceBox.setText(priceString);
            //If yes was selected before no, remove price from total
            if(mouseClicked == 1) {
                calculateTotalPrice(-mousePrice);
                mouseClicked = 2;
            }
        }
    }


    public void processorChosen(ActionEvent actionEvent) {
        String selectedItem = (String) processorChoiceBox.getValue();
        //Split string into name and price
        String[] selectedArray = selectedItem.split("\\$");
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-4));
        //Set pricebox text
        String priceString = "+"+curPrice+" USD";
        processorPriceBox.setText(priceString);
        //If this is not the first product chosen, first remove the last price added from total, then add the new total
        if(processorChosen) {
            calculateTotalPrice(-lastProcessorPriceAdded);
            calculateTotalPrice(curPrice);
            lastProcessorPriceAdded = curPrice;
        }
        //If first time selected, add price to total, and to lastProcessorPriceAdded to keep track of the price added, in case another product is chosen
        if(!processorChosen) {
            calculateTotalPrice(curPrice);
            lastProcessorPriceAdded = curPrice;
            processorChosen = true;
        }
    }

    public void graphicsChosen(ActionEvent actionEvent) {
        String selectedItem = (String) graphicsChoiceBox.getValue();
        //Split string into name and price
        String[] selectedArray = selectedItem.split("\\$");
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-4));
        //Set pricebox text
        String priceString = "+"+curPrice+" USD";
        graphicsPriceBox.setText(priceString);
        //If this is not the first product chosen, first remove the last price added from total, then add the new total
        if(graphicsChosen) {
            calculateTotalPrice(-lastGraphicsPriceAdded);
            calculateTotalPrice(curPrice);
            lastGraphicsPriceAdded = curPrice;
        }
        //If first time selected, add price to total, and to lastProcessorPriceAdded to keep track of the price added, in case another product is chosen
        if(!graphicsChosen) {
            calculateTotalPrice(curPrice);
            lastGraphicsPriceAdded = curPrice;
            graphicsChosen = true;
        }
    }

    public void ramChosen(ActionEvent actionEvent) {
        String selectedItem = (String) ramChoiceBox.getValue();
        //Split string into name and price
        String[] selectedArray = selectedItem.split("\\$");
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-4));
        //Set pricebox text
        String priceString = "+"+curPrice+" USD";
        ramPriceBox.setText(priceString);
        //If this is not the first product chosen, first remove the last price added from total, then add the new total
        if(ramChosen) {
            calculateTotalPrice(-lastRAMPriceAdded);
            calculateTotalPrice(curPrice);
            lastRAMPriceAdded = curPrice;
        }
        //If first time selected, add price to total, and to lastProcessorPriceAdded to keep track of the price added, in case another product is chosen
        if(!ramChosen) {
            calculateTotalPrice(curPrice);
            lastRAMPriceAdded = curPrice;
            ramChosen = true;
        }
    }

    public void driveChosen(ActionEvent actionEvent) {
        String selectedItem = (String) driveChoiceBox.getValue();
        //Split string into name and price
        String[] selectedArray = selectedItem.split("\\$");
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-4));
        //Set pricebox text
        String priceString = "+"+curPrice+" USD";
        drivePriceBox.setText(priceString);
        //If this is not the first product chosen, first remove the last price added from total, then add the new total
        if(driveChosen) {
            calculateTotalPrice(-lastDrivePriceAdded);
            calculateTotalPrice(curPrice);
            lastDrivePriceAdded = curPrice;
        }
        //If first time selected, add price to total, and to lastProcessorPriceAdded to keep track of the price added, in case another product is chosen
        if(!driveChosen) {
            calculateTotalPrice(curPrice);
            lastDrivePriceAdded = curPrice;
            driveChosen = true;
        }
    }

    public void screenChosen(ActionEvent actionEvent) {
        String selectedItem = (String) screenChoiceBox.getValue();
        //Split string into name and price
        String[] selectedArray = selectedItem.split("\\$");
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-4));
        //Set pricebox text
        String priceString = "+"+curPrice+" USD";
        screenPriceBox.setText(priceString);
        //If this is not the first product chosen, first remove the last price added from total, then add the new total
        if(screenChosen) {
            calculateTotalPrice(-lastScreenPriceAdded);
            calculateTotalPrice(curPrice);
            lastScreenPriceAdded = curPrice;
        }
        //If first time selected, add price to total, and to lastProcessorPriceAdded to keep track of the price added, in case another product is chosen
        if(!screenChosen) {
            calculateTotalPrice(curPrice);
            lastScreenPriceAdded = curPrice;
            screenChosen = true;
        }
    }
}
