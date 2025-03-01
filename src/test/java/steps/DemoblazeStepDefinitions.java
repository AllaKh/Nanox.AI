package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import service.Constants;
import service.DemoblazeTestService;
import service.UserCredentials;

import java.util.List;
import java.util.Map;

public class DemoblazeStepDefinitions {

    WebDriver driver;
    DemoblazeTestService demoblazeTestService;

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        driver = new ChromeDriver(); // Initialize WebDriver
        demoblazeTestService = new DemoblazeTestService(driver);
        demoblazeTestService.openLoginPage();
    }

    // ✅ Successful Login
    @When("User enters valid username {string} and password {string}")
    public void userEntersValidCredentials(String username, String password) {
        demoblazeTestService.enterCredentials(username, password);
        demoblazeTestService.submitLogin();
    }

    @Then("User should be redirected to the homepage")
    public void userShouldBeRedirectedToHomepage() {
        Assert.assertTrue("Login failed! User not redirected.", demoblazeTestService.isUserLoggedIn());
        driver.quit();
    }

    // ❌ Unsuccessful Login
    @When("User enters invalid credentials:")
    public void userEntersInvalidCredentials(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : credentials) {
            String username = row.get("username");
            String password = row.get("password");

            WebElement usernameField = driver.findElement(By.id("loginusername"));
            WebElement passwordField = driver.findElement(By.id("loginpassword"));

            usernameField.clear(); // Clear input field before entering values
            passwordField.clear();

            if (username != null) usernameField.sendKeys(username);
            if (password != null) passwordField.sendKeys(password);

            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
            loginButton.click();
        }
    }

    @Then("User should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert Assert;
        try {
            WebElement alert = driver.findElement(By.id("logInModal")); // Checking if modal still exists
            Assert.assertTrue(alert.isDisplayed());
            System.out.println("Error message displayed: Invalid credentials.");
        } catch (Exception e) {
            Assert.fail("No error message displayed for invalid login.");
        } finally {
            driver.quit(); // Close browser after test
        }
    }

    DemoblazeTestService formService;

    @Given("user is on the Demoblaze contact form page")
    public void userIsOnTheContactFormPage() {
        driver = new ChromeDriver();
        formService = new DemoblazeTestService(driver);
        formService.openContactFormPage();
    }

    // ✅ Successful Form Submission
    @When("user enters valid data in the form fields:")
    public void userEntersValidData() {
        UserCredentials userData = new UserCredentials(Constants.USER_NAME, Constants.USER_PASSWORD, Constants.USER_EMAIL, Constants.USER_MESSAGE);
        formService.fillContactForm(userData);
    }

    // ❌ Unsuccessful Form Submission (using data table for different invalid inputs)
    @When("user enters invalid data in the form fields:")
    public void userEntersInvalidData(DataTable dataTable) {
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
        driver.quit();
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {
        Assert.assertTrue("Error message not displayed!", formService.isErrorMessageDisplayed());
        driver.quit();
    }

    DemoblazeTestService searchAndNavigateService;
    @Given("User is on the homepage")
    public void userIsOnTheHomepage() {
        driver = new ChromeDriver();
        searchAndNavigateService = new DemoblazeTestService(driver);
        searchAndNavigateService.openHomePage();
    }

    @When("User searches for {string}")
    public void userSearchesFor(String query) {
        searchAndNavigateService.search(query);
    }

    @When("User searches with an empty term")
    public void userSearchesWithAnEmptyTerm() {
        searchAndNavigateService.search("");
    }

    @Then("The search results should contain {string}")
    public void theSearchResultsShouldContain(String expectedProduct) {
        Assert.assertTrue("Search results do not contain expected product!",
                searchAndNavigateService.isProductInSearchResults(expectedProduct));
        driver.quit();
    }

    @Then("User should see a {string} message")
    public void userShouldSeeMessage(String expectedMessage) {
        Assert.assertEquals("Search message is incorrect!", expectedMessage, searchAndNavigateService.getNoResultsMessage());
        driver.quit();
    }

    @Then("The system should display all available products")
    public void theSystemShouldDisplayAllAvailableProducts() {
        Assert.assertTrue("All products are not displayed!", searchAndNavigateService.isAllProductsDisplayed());
        driver.quit();
    }

    @When("User clicks on each navigation link in the header")
    public void userClicksOnEachNavigationLinkInTheHeader() {
        searchAndNavigateService.clickHeaderLinks();
    }

    @When("User clicks on each navigation link in the footer")
    public void userClicksOnEachNavigationLinkInTheFooter() {
        searchAndNavigateService.clickFooterLinks();
    }

    @Then("The correct pages should load with the expected URLs")
    public void theCorrectPagesShouldLoadWithTheExpectedURLs() {
        // Explicitly declare the Map with String keys and String values
        Map<String, String> failedUrls = searchAndNavigateService.verifyNavigation();
        Assert.assertTrue("Some pages did not load correctly: " + failedUrls, failedUrls.isEmpty());
        driver.quit();
    }
    @When("User clicks the logout button")
    public void userClicksTheLogoutButton() {
        demoblazeTestService.clickLogoutButton();
    }

    @Then("User should be logged out and redirected to the login page")
    public void userShouldBeLoggedOutAndRedirectedToTheLoginPage() {
        boolean isLoggedOut = demoblazeTestService.verifyLogout();
        Assert.assertTrue("User is not logged out successfully", isLoggedOut);
        driver.quit();
    }
}
