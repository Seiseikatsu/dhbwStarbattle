Feature: Registration is working if there are no errors

  Scenario: Normal Registration
    Given I am on the registration view
    When I type accname "Stiff"
    And I type disname "Stiff2"
    And I type password "test123!"
    And I type reppassword "test123!"
    And I type email "stiff@stiff.de"
    And I click on button "Register"
    Then I am on the lobby view


  Scenario: existing Accountname
    Given I am on the registration view
    When I type accname "TimeTester"
    And I type disname "TimoTester2"
    And I type password "test123!"
    And I type reppassword "test123!"
    And I type email "stiff@stiff.de"
    And I click on button "Register"
    Then I am on the registration view
    And I receive an error message saying "Existing Accountname"


  Scenario: existing Displayname
    Given I am on the registration view
    When I type accname "TimeTester"
    And I type disname "Tim"
    And I type password "test123!"
    And I type reppassword "test123!"
    And I type email "stiff@stiff.de"
    And I click on button "Register"
    Then I am on the registration view
    And I receive an error message saying "Existing Displayname"

