Feature: Navigation Links Functionality

  As a user,
  I want to navigate through different pages using the navigation bar and footer,
  So that I can access all important sections of the website.

  @TC8 @Navigation
  Scenario: Navigation through the header links
    Given User is on the homepage
    When User clicks on each navigation link in the header
    Then The correct pages should load with the expected URLs

  @TC9 @Navigation
  Scenario: Navigation through the footer links
    Given User is on the homepage
    When User clicks on each navigation link in the footer
    Then The correct pages should load with the expected URLs