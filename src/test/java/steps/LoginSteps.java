package steps;

import service.LoginService;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.CartPage;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.List;

public class LoginSteps {
    private final LoginService loginService;

    public LoginSteps(LoginService loginService) {
        this.loginService = loginService;
    }

    @Given("User is on the login page")
    public void user_is_on_login_page() {
        loginService.loginPage();
    }

    @When("User enters valid username {string} and password {string}")
    public void user_enters_credentials(String validUser, String validPassword) {
        loginService.enterValidCredentials(validUser, validPassword);
    }

    @Then("User should be redirected to the homepage")
    public void user_should_be_logged_in() {
        loginService.shouldBeRedirectedToTheHomepage();
    }

    @When("User enters invalid credentials")
    public void user_enters_invalid_credentials(String invalidUser, String invalidPassword) {
        loginService.enterInvalidCredentials(invalidUser, invalidPassword);
    }

    // Optional: Clean up by closing the browser after tests
    @After
    public void closeBrowser() {
        loginService.closeBrowser();
    }

    @When("User checks all links on the website")
    public void user_checks_all_links() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            assertTrue(link.isDisplayed());
            assertTrue(link.isEnabled());
        }
    }

    @When("User fills out the contact form with name {string}, email {string}, and message {string}")
    public void user_fills_contact_form(String name, String email, String message) {
        driver.findElement(By.id("contact-name")).sendKeys(name);
        driver.findElement(By.id("contact-email")).sendKeys(email);
        driver.findElement(By.id("contact-message")).sendKeys(message);
        driver.findElement(By.xpath("//button[text()='Send message']")).click();
    }

    @Then("User should see a success message")
    public void user_should_see_success_message() {
        assertTrue(driver.findElement(By.id("successMessage")).isDisplayed());
    }
}
