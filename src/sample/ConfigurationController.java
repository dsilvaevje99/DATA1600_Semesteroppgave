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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
    public String keyboardFinalChoice = "";
    public int mouseClicked = 0;  //0 if none clicked, 1 if yes clicked, 2 if no clicked
    public int mousePrice = 25;
    public String mouseFinalChoice = "";
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
        List<String> processorComponents = new ArrayList<>();
        List<String> graphicsComponents = new ArrayList<>();
        List<String> ramComponents = new ArrayList<>();
        List<String> driveComponents = new ArrayList<>();
        List<String> screenComponents = new ArrayList<>();
        //Loop through each line of components.txt file
        try {
            ComponentManager componentManager = new ComponentManager();
            ArrayList<Component> compList = componentManager.loadList();
            for (Component comp : compList) {
                //ERROR HANDLING: If a line contains errors, skip it and print error message
                if (comp != null) {
                    //ERROR HANDLING: make sure none of fields are empty
                    if (comp.getName() != null && comp.getType() != null && comp.getPrice() != null) {
                        //ERROR HANDLING: make sure price string is a valid double
                        boolean priceCheck;
                        try {
                            Double.parseDouble(comp.getPrice());
                            priceCheck = true;
                        } catch (NumberFormatException e) {
                            System.err.println(e);
                            priceCheck = false;
                        }
                        if(priceCheck) {
                            //ERROR HANDLING: make sure all strings match the regex
                            String componentTypeRegex = "[a-zA-Z '\\-]{3,20}";
                            String componentNameRegex = "[\"a-zA-Z 0-9()'\\-]{3,40}";
                            String componentPriceRegex = "[0-9.]{1,6}";
                            if(true) {
                                //Add component info to arrays sorted by component type
                                switch (comp.getType()) {
                                    case "Processor": {
                                        String item = comp.getName() + " ($" + comp.getPrice() + " USD)";
                                        processorComponents.add(item);
                                        break;
                                    }
                                    case "Hard drive": {
                                        String item = comp.getName() + " ($" + comp.getPrice() + " USD)";
                                        driveComponents.add(item);
                                        break;
                                    }
                                    case "Screen": {
                                        String item = comp.getName() + " ($" + comp.getPrice() + " USD)";
                                        screenComponents.add(item);
                                        break;
                                    }
                                    case "RAM": {
                                        String item = comp.getName() + " ($" + comp.getPrice() + " USD)";
                                        ramComponents.add(item);
                                        break;
                                    }
                                    case "Graphics card": {
                                        String item = comp.getName() + " ($" + comp.getPrice() + " USD)";
                                        graphicsComponents.add(item);
                                        break;
                                    }
                                    default:
                                        System.out.println("ERROR: Component type not recognized: " + comp.getType());
                                        break;
                                }
                            } else {
                                System.out.println("ERROR: One or more component attributes contains invalid characters, is too long, or too short: "+ comp.toString());
                            }
                        } else {
                            System.out.println("ERROR: Component price contains invalid characters: "+ comp.getPrice());
                        }
                    } else {
                        System.out.println("ERROR: One or more component attributes are empty: "+ compList.toString());
                    }
                } else {
                    System.out.println("ERROR: Component line contains invalid number of elements: "+ compList.toString());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
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
        //ERROR HANDLING: Make sure all fields have a value. If they don't have one, set the text of the error label and stop executing method.
        if(!processorChosen || !graphicsChosen || !ramChosen || !driveChosen || !screenChosen || keyboardFinalChoice.equals("") || mouseFinalChoice.equals("")) {
            errorLabel.setText("All fields must have a selection!");
            return;
        }

        int lastEntryNum = 0;
        //Get the number of the last entry to number the next entry correctly
        try {
            FileReader fr = new FileReader("./src/sample/User Files/configurations.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                //For each line, split at , and check the number of last entry with line[0]
                String[] lineArray = line.split(",");
                //Try to parse the int from file entry
                try {
                    //Add entry number to lastEntryNum so that it contains the number of the last entry of the file
                    lastEntryNum = Integer.parseInt(lineArray[0]);
                }
                //If it cannot parse, set last entry to 0 (meaning this will be the first entry of the file)
                catch(NumberFormatException e) {
                    lastEntryNum = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //create string from input values if everything is OK:
        String newEntryNum = Integer.toString(lastEntryNum+1);
        String newComponentString = System.lineSeparator()+newEntryNum+","+processorChoiceBox.getValue()+","+graphicsChoiceBox.getValue()+","+ramChoiceBox.getValue()+","+driveChoiceBox.getValue()+","+screenChoiceBox.getValue()+","+keyboardFinalChoice+","+mouseFinalChoice+","+totalPrice;
        //save to file
        Writer output;
        output = new BufferedWriter(new FileWriter("./src/sample/User Files/configurations.txt", true));
        output.append(newComponentString);
        output.close();
        //return to user page
        openUserScene(actionEvent);
    }

    public void calculateTotalPrice(int price) {
        totalPrice += price;
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
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-5));
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
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-5));
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
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-5));
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
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-5));
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
        int curPrice = Integer.parseInt(selectedArray[1].substring(0, selectedArray[1].length()-5));
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
