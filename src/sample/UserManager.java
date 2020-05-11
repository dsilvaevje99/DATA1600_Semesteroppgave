package sample;

import java.io.*;
import java.util.ArrayList;

public class UserManager {
    ArrayList<User> users = new ArrayList<>();
    private boolean list_changed = false;

    public UserManager(){
        try {
            users = loadList();
        } catch (Exception e) {
            System.err.println("error occured:" + e);
        }
    }

    public void create_user(String name, String password, boolean superUser) throws IOException, ClassNotFoundException {
        if (list_changed){
            users = loadList();
        }
        User user = new User(name, password, superUser);
        users.add(user);
        saveList(users);
        list_changed = true;
    }

    public boolean verify_user(String username, String password, boolean needSuperuser) throws IOException, ClassNotFoundException {
        if (list_changed){
            users = loadList();
        }
        for (User user : users){
            if (user.username.equals(username)){
                if (needSuperuser){
                    if (user.superUser){
                        return user.password.equals(password);
                    }else return false;
                }else return user.password.equals(password);
            }
        }
        return false;
    }

    private void saveList(ArrayList<User> users) throws IOException {
        FileOutputStream f_out = new FileOutputStream("./src/sample/Admin Files/users.file");
        ObjectOutputStream list_out = new ObjectOutputStream(f_out);
        list_out.writeObject(users);

    }
    private ArrayList<User> loadList() throws IOException, ClassNotFoundException{
        FileInputStream f_in = new
                FileInputStream("./src/sample/Admin Files/users.file");
        ObjectInputStream obj_in =
                new ObjectInputStream (f_in);
        Object obj = obj_in.readObject();
        if (obj instanceof ArrayList) users = (ArrayList<User>) obj;
        return users;
    }
}
