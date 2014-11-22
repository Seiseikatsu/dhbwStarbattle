Feature: Login is working if there are no errors

  Scenario: Normal Login
    Given I am on the login view    
    When I type name "TimoTester"
    And I type password "test12!"
    And I click on button "Login"
    Then I am on the lobby view


  Scenario: Wrong Password
    Given I am on the login view
    When I type name "TimoTester" 
    And I type password "test" 
    And I click on button "login"
    Then I am on the login view
    And I receive an error message saying "Wrong Password"


  Scenario: Wrong username
    Given I am on the login view
    When I type name "TimoTeste" 
    And I type password "test"
    And I click on button "login"
    Then I am on the login view
    And I receive an error message saying "Wrong Username"

