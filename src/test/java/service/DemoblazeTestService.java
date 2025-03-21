package service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DemoblazeTestService {
    private WebDriver driver;
    private final String homePageUrl = "https://www.demoblaze.com/index.html";
    private final String loginPageUrl = "https://www.demoblaze.com/login.html";
    private final String contactPageUrl = "https://www.demoblaze.com/contact.html";

    // Constructor that accepts a WebDriver
    public DemoblazeTestService(WebDriver driver) {
        WebDriverManager.chromedriver().setup();
        this.driver = driver; // Set the WebDriver instance
    }

    public String getPayload(String name, String password, String email, String message) throws JsonProcessingException {
        UserCredentials user = new UserCredentials(name, password, email, message);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    public void openLoginPage() {
        driver.get(loginPageUrl);
        driver.findElement(By.id("login2")).click();
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
        WebElement logoutButton = driver.findElement(By.id("logout2"));
        return logoutButton.isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement alert = driver.findElement(By.id("logInModal"));
            return alert.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openContactFormPage() {
        driver.get(contactPageUrl);
        driver.findElement(By.linkText("Contact")).click();
    }

    public void fillContactForm(UserCredentials userCredentials) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userCredentials);
        JsonNode jsonNode = objectMapper.readTree(json);

        String name = jsonNode.get("name").asText();
        String email = jsonNode.get("email").asText();
        String message = jsonNode.get("message").asText();
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

    public void openHomePage() {
        driver.get(homePageUrl);
    }

    public void search(String query) {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        WebElement searchButton = driver.findElement(By.id("searchBtn"));

        searchField.clear();
        searchField.sendKeys(query);
        searchButton.click();
    }

    public boolean isProductInSearchResults(String productName) {
        List<WebElement> results = driver.findElements(By.cssSelector(".card-title a"));
        for (WebElement result : results) {
            if (result.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public String getNoResultsMessage() {
        WebElement noResultsElement = driver.findElement(By.id("noResultsMessage"));
        return noResultsElement.getText();
    }

    public boolean isAllProductsDisplayed() {
        List<WebElement> allProducts = driver.findElements(By.cssSelector(".card-title a"));
        return !allProducts.isEmpty();
    }

    public void clickLinksAndVerify(Map<String, String> expectedLinks) {
        for (Map.Entry<String, String> entry : expectedLinks.entrySet()) {
            By linkLocator = By.linkText(entry.getKey());
            WebElement link = driver.findElement(linkLocator);
            link.click();
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(entry.getValue())) {
                System.out.println("Mismatch: Expected " + entry.getValue() + " but got " + currentUrl);
            }
            driver.navigate().back();
        }
    }

    public void verifyLink(Map<String, String> expectedLinks, Map<String, String> failedUrls) {
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
        waitForElement(By.id("login2"), 5);
        return driver.getCurrentUrl().equals(homePageUrl) &&
                driver.findElement(By.id("login2")).isDisplayed();
    }

    private void waitForElement(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}