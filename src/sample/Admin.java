package sample;

public class Admin extends Bruker {

    public Admin(){
        this.username = null;
        this.passord = null;
        this.superUser = false;
    }

    public Admin(String username, String password) {
        Password pass = new Password();
        this.username = username;
        this.passord = pass.create_password(password);
        this.superUser = true;
    }
}
