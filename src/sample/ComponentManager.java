package sample;

import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ComponentManager {
    ArrayList<Component> compList = new ArrayList<>();
    boolean list_changed = false;

    public ComponentManager(){
        try {
            compList = loadList();
        } catch (Exception e) {
            System.err.println("error occured:" + e);
        }
    }

    public void create_component(String name, String type, String price) {
        if (list_changed){
            try {
                compList = loadList();
            }catch (Exception e){
                System.out.println("list not found, starting from empty list");
            }
        }
        //ERROR HANDLING: validate input values? Display errors in compErrorLabel
        Component comp = new Component(name, type, price);
        compList.add(comp);
        //save to file
        try {
            saveList(compList);
        } catch (Exception e){
            System.err.println("error in saving file" + e);
        }
        list_changed = true;
    }

    public ArrayList<Component> sortList(String type){
        return compList.stream()
                .filter(p1 -> p1.getType().startsWith(type)).collect(Collectors.toCollection(ArrayList::new));
    }

    private void saveList(ArrayList<Component> compList) throws IOException {
        FileOutputStream f_out = new FileOutputStream("./src/sample/Admin Files/components.file");
        ObjectOutputStream list_out = new ObjectOutputStream(f_out);
        list_out.writeObject(compList);

    }
    public ArrayList<Component> loadList() throws IOException, ClassNotFoundException{
        FileInputStream f_in = new
                FileInputStream("./src/sample/Admin Files/components.file");
        ObjectInputStream obj_in =
                new ObjectInputStream (f_in);
        Object obj = obj_in.readObject();
        if (obj instanceof ArrayList) compList = (ArrayList<Component>) obj;
        return compList;
    }
}
