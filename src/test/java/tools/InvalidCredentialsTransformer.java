package tools;

import static tools.InvalidCredentials.
import io.cucumber.java.DataTableType;
import java.util.Map;

public class InvalidCredentialsTransformer {
    @DataTableType(replaceWithEmptyString = "[blank]")
    public InvalidCredentials transformEntry(Map<String, String> entry) {
        String username = entry.get("username");
        String password = entry.get("password");
        return new InvalidCredentials(username, password);
    }
}
