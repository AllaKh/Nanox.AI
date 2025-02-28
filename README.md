# Nanox.AI

# Demo Blaze Automated Testing with Selenium & Cucumber

## Overview

This project implements automated UI tests for the Demo Blaze application using **Selenium**, **Cucumber**, and **Java** following the **Page Object Model (POM)** structure.

## Prerequisites

Before running the tests, ensure you have the following installed:

- **Java JDK 11+**
- **Maven**
- **Git**
- **Google Chrome** and **ChromeDriver** (ensure they are compatible versions)

## Installation

1. Clone the repository:
   ```sh
   git clone git@github.com:AllaKh/Nanox.AI.git
   ```
2. Install dependencies:
   ```sh
   mvn clean install
   ```

## Running Tests

To execute tests, use the following command:

```sh
mvn test
```

To generate test reports:

```sh
mvn verify
```

## Define Test Scenarios

For this application, test scenarios should cover the most important features that the application provides, ensuring they function as expected.

1. **Login Test Scenario:**

   **Description:** Test the login functionality with valid and invalid credentials.
   **Reason:** Login is typically one of the most critical features. It needs to be tested for both valid and invalid user inputs to ensure the system correctly handles login attempts.
   **Steps:**
   1. Enter valid username and password.
   2. Click the login button.
   3. Verify the login is successful and the user is redirected to the homepage.
   4. Repeat for invalid credentials and check for appropriate error message.

2. **Form Submission Test Scenario:**

   **Description:** Test form submission with valid and invalid data.
   **Reason:** Forms are common in applications and often involve a series of validations, so testing form submission and input validation is vital.
   **Steps:**
   1. Enter valid data in the form fields.
   2. Submit the form.
   3. Verify that the form is submitted successfully and an appropriate confirmation message appears.
   4. Test with invalid input data and verify that the system correctly handles the errors.

3. **Search Functionality Test Scenario:**

   **Description:** Test the search functionality with different queries.
   **Reason:** Search is often a core feature in many applications, so ensuring it works as expected is crucial.
   **Steps:**
   1. Enter a search term.
   2. Verify that the results match the search term and that the UI displays the correct results.
   3. Test with an empty or invalid search term and verify that the system behaves correctly (e.g., showing no results or a message like "No results found").

4. **Navigation Test Scenario**

   **Description:** Test that all important navigation links work.
   **Reason:** Navigation is vital for usability, and ensuring that users can navigate between pages and sections is a must.
   **Steps:**
   1. Click on each navigation link in the header/footer.
   2. Verify that the page redirects to the correct URL and content.

5. **Logout Test Scenario:**

   **Description:** Test the logout functionality.
   **Reason:** After login, users should be able to log out. This feature should work without any issues.
   **Steps:**
   1. Log in with valid credentials.
   2. Click the logout button.
   3. Verify that the user is logged out and redirected to the login page.

## Bug Reports

If any issues are found, a detailed bug report will be included in the `bug_report.md` file.

## Suggestions for Improvement

A document (`improvement_suggestions.md`) is included with potential application enhancements in terms of UX, performance, and stability.

## 3-Month Automation Strategy Plan (Optional)

A roadmap for long-term test automation is provided in automation_strategy.md.
