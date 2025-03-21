package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
import java.time.Duration;
import org.junit.Assert;
import service.UserCredentials;
import service.DemoblazeTestService;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.io.IOException;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import java.time.Clock;

public class DemoblazeStepDefinitions {

    private WebDriver driver;
    private DemoblazeTestService demoblazeTestService;
    private Map<String, String> failedUrls = new ConcurrentHashMap<>();

    @Before
    public void setUp() {
        try {
            driver = new ChromeDriver(); // Initialize WebDriver
            demoblazeTestService = new DemoblazeTestService(driver); // Correctly call the constructor
            demoblazeTestService.openLoginPage(); // Open the login page
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cannot initialize WebDriver: " + e.getMessage());
        }
    }

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        try {
            driver.get("https://www.demoblaze.com/index.html");
            WebElement loginButton = driver.findElement(By.id("login2"));
            loginButton.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Successful Login
    @When("User enters valid username {string} and password {string}")
    public void userEntersValidCredentials(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));
        loginButton.click();
    }

    @Then("User should be redirected to the homepage")
    public void userShouldBeRedirectedToHomepage() {
        Assert.assertTrue("Login failed! User not redirected.", demoblazeTestService.isUserLoggedIn());
    }

    // ❌ Unsuccessful Login
    @When("User enters invalid credentials:")
    public void userEntersInvalidCredentials(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : credentials) {
            String username = row.get("username");
            String password = row.get("password");

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
                WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));

                usernameField.clear(); // Clear field before entering new value
                passwordField.clear();

                if (username != null) usernameField.sendKeys(username);
                if (password != null) passwordField.sendKeys(password);

                WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));
                loginButton.click();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                Assert.fail("Element not found: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("An error occurred: " + e.getMessage());
            }
        }
    }

    @Then("User should see an error message")
    public void userShouldSeeAnErrorMessage() {
        try {
            WebElement alert = driver.findElement(By.id("logInModal")); // Проверка, что модальное окно все еще отображается
            Assert.assertTrue(alert.isDisplayed());
            System.out.println("Error message: Wrong credentials.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error message doesn't display for wrong enterance attempt: " + e.getMessage());
        }
    }

    DemoblazeTestService formService;

    @Given("user is on the Demoblaze contact form page")
    public void userIsOnTheContactFormPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        formService.openContactFormPage();
    }

    // ✅ Successful Form Submission
    @When("user enters valid data in the form fields:")
    public void userEntersValidData(DataTable dataTable) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String name = row.get("name");
            String password = row.get("password");
            String email = row.get("email");
            String message = row.get("message");
            UserCredentials userData = new UserCredentials(name, password, email, message);
            formService.fillContactForm(userData);
        }
    }

    // ❌ Unsuccessful Form Submission (using data table for different invalid inputs)
    @When("user enters invalid data in the form fields:")
    public void userEntersInvalidData(DataTable dataTable) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : credentialsList) {
            // Initialize UserCredentials from the data table
            UserCredentials user = new UserCredentials(
                    row.getOrDefault("name", ""),
                    row.getOrDefault("password", ""),
                    row.getOrDefault("email", ""),
                    row.getOrDefault("message", "")
            );
            formService.fillContactForm(user); // Pass the user object to the formService
        }
    }

    @When("user submits the form")
    public void userSubmitsTheForm() {
        formService.submitForm();
    }

    @Then("user should see a confirmation message")
    public void userShouldSeeAConfirmationMessage() {
        Assert.assertTrue("Confirmation message not displayed!", formService.isConfirmationMessageDisplayed());
    }

    @Then("user should see an error message on form submission")
    public void userShouldSeeAnErrorMessageOnFormSubmission() {
        Assert.assertTrue("Error message not displayed!", formService.isErrorMessageDisplayed());
    }

    @Given("User is on the homepage")
    public void userIsOnTheHomepage() {
        demoblazeTestService.openHomePage();
    }

    @When("User searches for {string}")
    public void userSearchesFor(String query) {
        demoblazeTestService.search(query);
    }

    @When("User searches with an empty term")
    public void userSearchesWithAnEmptyTerm() {
        demoblazeTestService.search("");
    }

    @Then("The search results should contain {string}")
    public void theSearchResultsShouldContain(String expectedProduct) {
        Assert.assertTrue("Search results do not contain expected product!",
                demoblazeTestService.isProductInSearchResults(expectedProduct));
    }

    @Then("User should see a {string} message")
    public void userShouldSeeMessage(String expectedMessage) {
        Assert.assertEquals("Search message is incorrect!", expectedMessage, demoblazeTestService.getNoResultsMessage());
    }

    @Then("The system should display all available products")
    public void theSystemShouldDisplayAllAvailableProducts() {
        Assert.assertTrue("All products are not displayed!", demoblazeTestService.isAllProductsDisplayed());
    }

    @When("User clicks on each navigation link in the header")
    public void userClicksOnEachNavigationLinkInTheHeader() {
        Map<String, String> expectedLinks = new HashMap<>();
        expectedLinks.put("Home", "https://www.demoblaze.com/index.html");
        expectedLinks.put("Contact", "https://www.demoblaze.com/contact.html");
        expectedLinks.put("About Us", "https://www.demoblaze.com/about.html");
        expectedLinks.put("Cart", "https://www.demoblaze.com/cart.html");
        expectedLinks.put("Login", "https://www.demoblaze.com/login.html");
        expectedLinks.put("Sign Up", "https://www.demoblaze.com/signup.html");

        By homeLink = By.linkText("Home");
        By contactLink = By.linkText("Contact");
        By aboutUsLink = By.linkText("About us");
        By cartLink = By.linkText("Cart");
        By loginLink = By.linkText("Log in");
        By signUpLink = By.linkText("Sign up");

        // Use the service to click on links and verify URLs
        demoblazeTestService.clickLinksAndVerify(expectedLinks);
    }

    @Then("The correct pages should load with the expected URLs")
    public void theCorrectPagesShouldLoadWithTheExpectedURLs() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String expectedUrlFragment = "expected";
        wait.until(urlContains(expectedUrlFragment));
    }

    @When("User clicks the logout button")
    public void userClicksTheLogoutButton() {
        demoblazeTestService.clickLogoutButton();
    }

    @Then("User should be logged out and redirected to the login page")
    public void userShouldBeLoggedOutAndRedirectedToTheLoginPage() {
        boolean isLoggedOut = demoblazeTestService.verifyLogout();
        Assert.assertTrue("User is not logged out successfully", isLoggedOut);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}