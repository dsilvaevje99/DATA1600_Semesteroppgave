package sample;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    String password;
    boolean superUser;

    public User(){
        this.username = null;
        this.password = null;
        this.superUser = false;
    }

    public User(String username, String password, boolean superUser){
        this.username = username;
        this.password = password;
        this.superUser = superUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    private static final long serialVersionUID = 6929685098267757690L;
}
