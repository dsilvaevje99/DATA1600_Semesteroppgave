package sample;

public class Bruker {
    String username;
    byte[] passord;
    boolean superUser;

    public Bruker(){
        this.username = null;
        this.passord = null;
        this.superUser = false;
    }

    public Bruker(String username, String password){
        Password pass = new Password();
        this.username = username;
        this.passord = pass.create_password(password);
        this.superUser = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return passord;
    }

    public void setPassword(byte[] password) {
        this.passord = password;
    }

    public boolean isSuperUser() {
        return superUser;
    }
}
