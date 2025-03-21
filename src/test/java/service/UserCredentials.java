package service;

public class UserCredentials {
    public static final String USER_NAME = "Tom Smith";
    public static final String USER_MESSAGE = "This is a valid test message.";
    public static final String USER_EMAIL = "example@google.com";
    public static final String USER_PASSWORD = "password1";

    private String name = USER_NAME;
    private String password = USER_PASSWORD;
    private String email = USER_EMAIL;
    private String message = USER_MESSAGE;

    // Constructor to initialize UserCredentials
    public UserCredentials(String name, String password, String email, String message) {
        this.name = (name != null) ? name : "";
        this.password = (password != null) ? password : "";
        this.email = (email != null) ? email : "";
        this.message = (message != null) ? message : "";
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