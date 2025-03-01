package service;

public class UserCredentials {
    private String name;
    private String password;
    private String email;
    private String message;

    // Constructor to initialize UserCredentials
    public UserCredentials(String name, String password, String email, String message) {
        this.name = name;
        this.password = (password != null) ? password : "";
        this.email = email;
        this.message = message;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}