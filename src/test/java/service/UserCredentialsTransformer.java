package service;

import io.cucumber.java.DataTableType;
import java.util.Map;

public class UserCredentialsTransformer {

    @DataTableType
    public UserCredentials transform(Map<String, String> row) {
        return new UserCredentials(
                row.getOrDefault("name", Constants.DEFAULT_USER_NAME),
                row.getOrDefault("email", Constants.DEFAULT_MAIL),
                row.getOrDefault("message", Constants.DEFAULT_MESSAGE)
        );
    }
}
