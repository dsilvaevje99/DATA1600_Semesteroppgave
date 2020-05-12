package sample;

public class Configuration {
    private String number;
    private String processor;
    private String motherboard;
    private String graphics;
    private String ram;
    private String hdd;
    private String keyboard;
    private String mouse;
    private String total;


    public Configuration(String number, String processor, String motherboard, String graphics, String ram, String hdd, String keyboard, String mouse, String total) {
        this.number = number;
        this.processor = processor;
        this.motherboard = motherboard;
        this.graphics = graphics;
        this.ram = ram;
        this.hdd = hdd;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.total = total;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
