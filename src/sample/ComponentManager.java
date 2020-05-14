package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentManager {
    List<Component> compList = new ArrayList<>();
    boolean list_changed = false;

    public ComponentManager(){
        try {
            compList = loadList();
        } catch (Exception e) {
            System.err.println("ERROR: An error occured: " + e);
        }
    }

    public void create_component(String name, String type, String price) {
        //refreshes list if it has been changed since last time
        if (list_changed){
            try {
                compList = loadList();
            }catch (Exception e){
                System.out.println("CREATE_COMPONENT: list not found, starting from empty list.");
            }
        }
        Component comp = new Component(name, type, price);
        compList.add(comp);
        //save to file
        try {
            //sorts list and saves it
            saveList(sortList(compList));
        } catch (Exception e){
            System.err.println("ERROR: Couldnt save component to file: " + e);
        }
        //indicates list has been changed
        list_changed = true;
    }

    public void remove_component(String query) throws IOException, ClassNotFoundException {
        if (list_changed){
            compList = loadList();
        }
        compList.removeIf(comp -> comp.getName().contains(query));
        saveList(compList);
        list_changed = true;
    }

    public List<Component> refineList(String type){
        // returns whole list
        if (type.equals("All")){
            return compList;
        }else {
            //returns only the components indicated by type
            return compList.stream()
                    .filter(p1 -> p1.getType().startsWith(type)).collect(Collectors.toCollection(ArrayList::new));
        }
    }

    //sorts list first by type then by name
    private List<Component> sortList(List<Component> compList){
        Comparator<Component> compareByName = Comparator
                .comparing(Component::getType)
                .thenComparing(Component::getName);

        return compList.stream()
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    //saves list to file
    private void saveList(List<Component> compList) throws IOException {
        FileOutputStream f_out = new FileOutputStream("./src/sample/Admin Files/components.file");
        ObjectOutputStream list_out = new ObjectOutputStream(f_out);
        list_out.writeObject(compList);

    }

    //loads list from file
    public List<Component> loadList() throws IOException, ClassNotFoundException{
        FileInputStream f_in = new
                FileInputStream("./src/sample/Admin Files/components.file");
        ObjectInputStream obj_in =
                new ObjectInputStream (f_in);
        Object obj = obj_in.readObject();
        if (obj instanceof ArrayList) compList = (List<Component>) obj;
        return compList;
    }
}
