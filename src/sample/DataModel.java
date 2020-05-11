package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataModel {
    private SimpleStringProperty nameData;
    private SimpleStringProperty typeData;
    private SimpleIntegerProperty priceData;

    public DataModel(String nameData, String typeData, int priceData) {
        this.nameData = new SimpleStringProperty(nameData);
        this.typeData = new SimpleStringProperty(typeData);
        this.priceData = new SimpleIntegerProperty(priceData);
    }

    public String getNameData() {
        return nameData.getValue();
    }
    public String getTypeData() {
        return typeData.getValue();
    }
    public int getPriceData() {
        return priceData.getValue();
    }

    public void setNameData(String fNameData) { this.nameData.set(fNameData); }
    public void setTypeData(String lNameData) { this.typeData.set(lNameData); }
    public void setPriceData(int ageData) { this.priceData.set(ageData); }
}
