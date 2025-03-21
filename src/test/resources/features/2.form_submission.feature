Feature: Form submission functionality on Demoblaze

  @TC3 @Form
  Scenario: Successful form submission with valid data
    Given user is on the Demoblaze contact form page
    When user enters valid data in the form fields:
      | name    | email           | message       |
      |---------|-----------------|---------------|
      | Tom     | tom@mail.com    | Test message  |
      | Alice   | alice@mail.com  | Hello world   |
    And user submits the form
    Then user should see a confirmation message

  @TC4 @Form @Negative
  Scenario: Unsuccessful form submission with invalid data
    Given user is on the Demoblaze contact form page
    When user enters invalid data in the form fields:
      | name      | email             | message         |
      | --------- | ----------------- | --------------- |
      |           | yz                |                 |
      | ""        | 567               |                 |
    And user submits the form
    Then user should see an error message on form submission