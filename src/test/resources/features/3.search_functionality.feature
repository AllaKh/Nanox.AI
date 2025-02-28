Feature: Search Functionality

  As a user, I want to search for products so that I can find what I need.

  @TC5 @Search
  Scenario: Successful search with a valid term
  Given User is on the homepage
  When User searches for "Samsung"
  Then The search results should contain "Samsung"

  @TC6 @Search @Negative
  Scenario: No results found for an invalid search term
  Given User is on the homepage
  When User searches for "invalidProduct123"
  Then User should see a "No results found" message

  @TC7 @Search
  Scenario: Search with an empty term
  Given User is on the homepage
  When User searches with an empty term
  Then The system should display all available products