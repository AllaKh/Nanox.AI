package service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormSubmissionService {

    WebDriver driver = new ChromeDriver(); // Initialize WebDriver (use your preferred WebDriver)

    public void userOnTheContactFormPage() {
        driver.get("https://www.demoblaze.com/index.html"); // Navigate to the Demoblaze homepage
        WebElement contactLink = driver.findElement(By.id("contact")); // Find the contact link
        contactLink.click(); // Click the contact link to navigate to the contact page
    }

    @When("user enters valid data in the form fields")
    public void userEntersValidDataInTheFormFields() {
        WebElement nameField = driver.findElement(By.id("recipient-name"));
        WebElement emailField = driver.findElement(By.id("recipient-email"));
        WebElement messageField = driver.findElement(By.id("message-text"));

        nameField.sendKeys("Alla Khananashvili"); // Enter a valid name
        emailField.sendKeys("akhnv@yahoo.com"); // Enter a valid email address
        messageField.sendKeys("This is a valid test message."); // Enter a valid message
    }

    @When("user submits the form")
    public void userSubmitsTheForm() {
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Send message')]"));
        submitButton.click(); // Click the submit button to send the form
    }

    @Then("user should see a confirmation message")
    public void userShouldSeeAConfirmationMessage() {
        // Verify that the confirmation message appears
        WebElement confirmationMessage = driver.findElement(By.className("success-message")); // Assuming a class for confirmation message
        assertTrue(confirmationMessage.isDisplayed()); // Check if the confirmation message is displayed
        assertEquals("Message sent successfully.", confirmationMessage.getText()); // Validate the message
    }

    @When("user enters invalid data in the form fields")
    public void userEntersInvalidDataInTheFormFields() {
        WebElement nameField = driver.findElement(By.id("recipient-name"));
        WebElement emailField = driver.findElement(By.id("recipient-email"));
        WebElement messageField = driver.findElement(By.id("message-text"));

        nameField.sendKeys(""); // Enter an empty name (invalid)
        emailField.sendKeys("invalid-email"); // Enter an invalid email
        messageField.sendKeys(""); // Enter an empty message (invalid)
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {
        // Verify that the error message appears
        WebElement errorMessage = driver.findElement(By.className("error-message")); // Assuming a class for error message
        assertTrue(errorMessage.isDisplayed()); // Check if the error message is displayed
        assertEquals("Please fill out this field.", errorMessage.getText()); // Validate the error message
    }

    // Optional: Clean up by closing the browser after tests
    @After
    public void closeBrowser() {
        driver.quit(); // Close the browser
    }

}
