@wip
  Feature: Login

    Scenario: As a user should login with valid credential
      Given I make a request with valid credentials
      Then Verify response with status code 200 and response body