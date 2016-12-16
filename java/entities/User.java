package entities;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: AdminController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
public class User {

    private String username;
    private String password;
    private String id;
    private boolean valid;
    private boolean isAdmin;

    public User() {

    }

    public User(String username, String password, String id, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean newValid) {
        valid = newValid;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
