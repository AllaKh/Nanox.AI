package service;

import io.cucumber.java.DataTableType;
import java.util.Map;

public class UserCredentialsTransformer {

    @DataTableType
    public UserCredentials transformEntry(Map<String, String> row) {
        return new UserCredentials(
                replaceBlankWithEmpty(row.getOrDefault("username", UserCredentials.USER_NAME)),
                replaceBlankWithEmpty(row.getOrDefault("password", UserCredentials.USER_PASSWORD)),
                replaceBlankWithEmpty(row.getOrDefault("email", UserCredentials.USER_EMAIL)),
                replaceBlankWithEmpty(row.getOrDefault("message", UserCredentials.USER_MESSAGE))
        );
    }

    private String replaceBlankWithEmpty(String value) {
        return "[blank]".equals(value) ? "" : value;
    }
}