package service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

public class DemoblazeTestService {

    private final WebDriver driver;

    public DemoblazeTestService(WebDriver driver) {
        this.driver = driver;
        expectedFooterLinks = Map.of(
                "Privacy Policy", "https://www.demoblaze.com/privacy.html",
                "Terms & Conditions", "https://www.demoblaze.com/terms.html"
        );
    }

    private final String homePageUrl = "https://www.demoblaze.com/index.html";
    private final String loginPageUrl = "https://www.demoblaze.com/login.html";
    private final String contactPageUrl = "https://www.demoblaze.com/contact.html";

    public static String getPayload(String name, String password, String email, String message) throws JsonProcessingException {
        UserCredentials user = new UserCredentials(name, password, email, message);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(user);
    }

    public static void createMessage(String[] args) {
        String name = Constants.USER_NAME;
        String email = Constants.USER_EMAIL;
        String message = Constants.USER_MESSAGE;

        service.DemoblazeTestService Utils;
        String jsonPayload = Utils.getPayload(name, email, message);
        System.out.println(jsonPayload);
    }

    public void openLoginPage() {
        driver.get(loginPageUrl); // Open site
        driver.findElement(By.id("login2")).click(); // Click on login button
    }

    public void enterCredentials(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        WebElement passwordField = driver.findElement(By.id("loginpassword"));

        usernameField.clear();
        passwordField.clear();

        if (username != null) usernameField.sendKeys(username);
        if (password != null) passwordField.sendKeys(password);
    }

    public void submitLogin() {
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
    }

    public boolean isUserLoggedIn() {
        try {
            WebElement logoutButton = driver.findElement(By.id("logout2")); // Logout button appears after login
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement alert = driver.findElement(By.id("logInModal")); // Check if modal is still open
            return alert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openContactFormPage() {
        driver.get(contactPageUrl);
        driver.findElement(By.linkText("Contact")).click();
    }

    public void fillContactForm(UserCredentials jsonPayload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonPayload);

        String name = jsonNode.get("name").asText();
        String email = jsonNode.get("email").asText();
        String message = jsonNode.get("message").asText();

        WebElement nameField = driver.findElement(By.id("recipient-name"));
        WebElement emailField = driver.findElement(By.id("recipient-email"));
        WebElement messageField = driver.findElement(By.id("message-text"));

        nameField.clear();
        emailField.clear();
        messageField.clear();

        nameField.sendKeys(name);
        emailField.sendKeys(email);
        messageField.sendKeys(message);

    }

    public void submitForm() {
        driver.findElement(By.xpath("//button[text()='Send message']")).click();
    }

    public boolean isConfirmationMessageDisplayed() {
        try {
            WebElement alert = driver.findElement(By.xpath("//div[contains(text(), 'Thanks for the message')]"));
            return alert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayedOnPage() {
        try {
            WebElement alert = driver.findElement(By.xpath("//div[contains(text(), 'Please fill out')]"));
            return alert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openHomePage() {
        driver.get(homePageUrl);
    }

    public void search(String query) {
        WebElement searchField = driver.findElement(By.id("searchBox")); // Update with actual search field ID
        WebElement searchButton = driver.findElement(By.id("searchBtn")); // Update with actual search button ID

        searchField.clear();
        searchField.sendKeys(query);
        searchButton.click();
    }

    public boolean isProductInSearchResults(String productName) {
        List<WebElement> results = driver.findElements(By.cssSelector(".card-title a")); // Update locator if needed
        for (WebElement result : results) {
            if (result.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public String getNoResultsMessage() {
        WebElement noResultsElement = driver.findElement(By.id("noResultsMessage")); // Update with actual locator
        return noResultsElement.getText();
    }

    public boolean isAllProductsDisplayed() {
        List<WebElement> allProducts = driver.findElements(By.cssSelector(".card-title a")); // Update locator
        return !allProducts.isEmpty();
    }

    private final Map<String, String> expectedHeaderLinks = Map.of(
            "Home", "https://www.demoblaze.com/index.html",
            "Contact", "https://www.demoblaze.com/contact.html",
            "About Us", "https://www.demoblaze.com/about.html",
            "Cart", "https://www.demoblaze.com/cart.html",
            "Login", "https://www.demoblaze.com/login.html",
            "Sign Up", "https://www.demoblaze.com/signup.html"
    );

    private final Map<String, String> expectedFooterLinks;

    public void clickHeaderLinks() {
        clickLinksAndVerify(expectedHeaderLinks, By.cssSelector(".nav-item a"));  // Update locator if necessary
    }

    public void clickFooterLinks() {
        clickLinksAndVerify(expectedFooterLinks, By.cssSelector(".footer a"));  // Update locator if necessary
    }

    private void clickLinksAndVerify(Map<String, String> expectedLinks, By locator) {
        List<WebElement> links = driver.findElements(locator);
        for (WebElement link : links) {
            String linkText = link.getText().trim();
            if (expectedLinks.containsKey(linkText)) {
                link.click();
                String currentUrl = driver.getCurrentUrl();
                if (!currentUrl.equals(expectedLinks.get(linkText))) {
                    System.err.println("Mismatch for " + linkText + ": Expected " + expectedLinks.get(linkText) + ", but got " + currentUrl);
                }
                driver.navigate().back(); // Navigate back to homepage
            }
        }
    }

    public Map<String, String> verifyNavigation() {
        Map<String, String> failedUrls = new HashMap<>();
        verifyLink(expectedHeaderLinks, failedUrls);
        verifyLink(expectedFooterLinks, failedUrls);
        return failedUrls;
    }

    private void verifyLink(Map<String, String> expectedLinks, Map<String, String> failedUrls) {
        for (Map.Entry<String, String> entry : expectedLinks.entrySet()) {
            driver.get(entry.getValue());
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(entry.getValue())) {
                failedUrls.put(entry.getKey(), currentUrl);
            }
        }
    }

    public void clickLogoutButton() {
        WebElement logoutButton = driver.findElement(By.id("logout2"));
        logoutButton.click();
    }

    public boolean verifyLogout() {
        // Wait for the login button to reappear
        waitForElement(By.id("login2"), 5);

        // Check if we are redirected to the login page or login button is visible
        return driver.getCurrentUrl().equals(homePageUrl) &&
                driver.findElement(By.id("login2")).isDisplayed();
    }

    private void waitForElement(By locator, int timeoutSeconds) {
        try {
            Thread.sleep(timeoutSeconds * 1000); // Use WebDriverWait in a real-world scenario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}