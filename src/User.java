public class User {
    private String uname;
    private String password;

    public User(String username, String password) {
        this.uname = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return uname;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.uname = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
