package COURIER;

public class Courier {


    private String login; //правило ооп поэтому приватные
    private String password;
    private String firstName;

    public Courier (String login,String password, String firstName){
        this.login = login;
        this.firstName = firstName;
        this.password = password;
    }
    public Courier() {
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}