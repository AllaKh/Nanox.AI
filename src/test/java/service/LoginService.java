package service;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class LoginService {

    WebDriver driver = new ChromeDriver(); // Initialize WebDriver (use your preferred WebDriver)

    public void loginPage() {
        driver.get("https://www.demoblaze.com/index.html"); // Navigate to the Demoblaze homepage
        WebElement loginButton = driver.findElement(By.id("login2")); // Find the login button
        loginButton.click(); // Click the login button to open the login modal
        System.out.println("Navigating to login page...");
    }

    public void enterValidCredentials(String validUser, String validPassword) {
        WebElement usernameField = driver.findElement(By.id("loginusername")); // Find username field
        WebElement passwordField = driver.findElement(By.id("loginpassword")); // Find password field
        WebElement loginSubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]")); // Find login button
        usernameField.sendKeys("validUser"); // Enter valid username
        passwordField.sendKeys("validPassword"); // Enter valid password
        loginSubmitButton.click(); // Click the login button to submit the login form
    }

    public void shouldBeRedirectedToTheHomepage() {
        System.out.println("Verifying the homepage...");
        // Check if the user is redirected to the homepage
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("index.html")); // Verify that the URL contains "index.html"
    }

    public void enterInvalidCredentials(String invalidUser, String invalidPassword) {
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        WebElement loginSubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));

        usernameField.sendKeys("invalidUser"); // Enter invalid username
        passwordField.sendKeys("invalidPassword"); // Enter invalid password
        loginSubmitButton.click(); // Click the login button to submit the form
    }

    public void closeBrowser() {
        driver.quit(); // Close the browser

}
