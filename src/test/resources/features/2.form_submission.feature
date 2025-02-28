Feature: Form submission functionality on Demoblaze

  @TC3
  Scenario: Successful form submission with valid data
  Given user is on the Demoblaze contact form page
  When user enters valid data in the form fields
  And user submits the form
  Then user should see a confirmation message

  @TC4
  Scenario: Unsuccessful form submission with invalid data
  Given user is on the Demoblaze contact form page
  When user enters invalid data in the form fields
  And user submits the form
  Then user should see an error message