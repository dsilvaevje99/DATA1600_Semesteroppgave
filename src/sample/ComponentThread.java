package sample;

import javafx.concurrent.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComponentThread extends Task<List<Component>> {
    private final String type;

    public ComponentThread(String type) {
        this.type = type;
    }

    @Override
    protected List<Component> call() throws InterruptedException {
        //Some seconds delay
        TimeUnit.SECONDS.sleep(3);

        //Get components
        ComponentManager componentManager = new ComponentManager();
        List<Component> sortedList = componentManager.refineList(type);

        //Return the list of components
        return sortedList;
    }

}
