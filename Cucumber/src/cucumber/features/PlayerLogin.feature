Feature: Login is working if there are no errors

  Scenario: Check if login is working correct
    Given: I am on the login view    
    When: I click on the login button
    And: Name and password typed in form
    Then: I should be in the lobby view

