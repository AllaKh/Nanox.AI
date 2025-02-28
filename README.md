# Nanox.AI

Demo Blaze Automated Testing with Selenium & Cucumber

**Overview**

This project implements automated UI tests for the Demo Blaze application using Selenium, Cucumber, and Java following the Page Object Model (POM) structure.

**Prerequisites**

Before running the tests, ensure you have the following installed:

Java JDK 11+

Maven

Git

Google Chrome and ChromeDriver (ensure they are compatible versions)

**Installation**

Clone the repository:

git clone <repository-url>
cd demo-blaze-tests

Install dependencies:

mvn clean install

**Running Tests**

To execute tests, use the following command:

mvn test

To generate test reports:

mvn verify

**Test Scenarios**

The following test scenarios must be definded:

_User Registration_

Verify that a user can successfully register with valid credentials.
Verify negative scenario: user cannot register with nonvalid credentials including field validation

Reason: Critical for user engagement and account creation.

_User Login_

Verify that a registered user can log in successfully.
Verify negative scenario: user cannot log if wrong credentials were entered
Verify field validation

Reason: Ensures authentication functionality works.

_Adding a Product to Cart_

Verify that a user can add a product to the cart and see it listed.
Verify that a user can delete a product from cart and see it not listed any more.

Reason: Core functionality for e-commerce.

_Completing a Purchase_

Verify that a user can complete a purchase after adding items to the cart.
Verify that a user can cancel a purchase before it was completed.

Reason: Ensures checkout and payment flow work correctly.

_Logout Functionality_

Verify that a logged-in user can successfully log out.

Reason: Security and session management validation.

**Bug Reports**

If any issues are found, a detailed bug report will be included in the bug_report.md file.

**Suggestions for Improvement**

A document (improvement_suggestions.md) is included with potential application enhancements in terms of UX, performance, and stability.

3-Month Automation Strategy Plan (Optional)

A roadmap for long-term test automation is provided in automation_strategy.md.
