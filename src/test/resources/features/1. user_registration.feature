Feature: Login functionality

  @TC1 @Login
  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User enters valid username "testuser" and password "Abc123"
    Then User should be redirected to the homepage

  @TC2 @Login @Negative
  Scenario Outline: Unsuccessful login with invalid credentials - <username> and <password>
    Given User is on the login page
    When User enters invalid credentials:
      | <username> | <password> |
    Then User should see an error message

    Examples:
      | username      | password |
      | Unknown       | wrong    |
      | [blank]       | [blank]  |
      |               |          |
      | ''            | ''       |
      | !@#$%^&*(+    |          |
      | 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678905555567%^&*(+ |          |