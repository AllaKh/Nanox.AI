Feature: Logout Functionality

  As a logged-in user,
  I want to be able to log out from the system,
  So that I can securely end my session.

  @TC10
  Scenario: Successful logout
    Given User is on the login page
    And User enters valid username "testuser" and password "Abc123"
    When User clicks the logout button
    Then User should be logged out and redirected to the login page