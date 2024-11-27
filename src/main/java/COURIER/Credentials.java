package COURIER;


public class Credentials {

    private String login; //правило ооп поэтому приватные
    private String password;


    public Credentials (String login,String password){
        this.login = login;
        this.password = password;
    }
    public Credentials() {
    }

    public static Credentials from (Courier courier) {

        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}