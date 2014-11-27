Feature: Login is working if there are no errors

  Scenario: Normal Login
    Given I start my application
    And I am on the login view
    When I type "TimoTester" in "Login_Name"
    And I type "Timotest#1" in "Login_Password"
    And I click on button "Button_Login"
    Then I am on the lobby view
 

  Scenario: Wrong Password
    Given I start my application
    And I am on the login view
    When I type "TimoTester" in "Login_Name"
    And I type "irgendeinmist" in "Login_Password"
    And I click on button "Button_Login"
    Then I am on the login view
    And I receive an error message saying "Wrong Password"
 
  Scenario: Wrong username
    Given I start my application
    And I am on the login view
    When I type "TimoTest" in "Login_Name"
    And I type "Timotest#1" in "Login_Password"
    And I click on button "Button_Login"
    Then I am on the login view
    And I receive an error message saying "Wrong Username"
 
  Scenario: User already logged in
    Given another application is logged in with "TimoTester" and "Timotest#1"
    And I start my application
    And I am on the login view
    When I type "TimoTester" in "Login_Name"
    And I type "Timotest#1" in "Login_Password"
    And I click on button "Button_Login"
    Then I am on the login view
    And I receive an error message saying "User already logged in"
 