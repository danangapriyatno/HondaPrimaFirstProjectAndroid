package PembuatanToken;

/**
 * Created by PRIMA on 10/19/2017.
 */

public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private  int id;
    private  String email;
    private  String token;

    public User(int id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }
}
