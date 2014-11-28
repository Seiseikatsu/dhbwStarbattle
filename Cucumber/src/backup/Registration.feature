Feature: Registration is working if there are no errors

  Scenario: Normal Registration
    Given I am on the login view
    When I click on button "Button_Register"
    And I type "Stiff" in "Register_Accountname"
    And I type "Stiff2" in "Register_Displayname"
    And I type "test123!" in "Register_Password"
    And I type "test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the lobby view
    And I delete user "Stiff"

  Scenario: existing Accountname
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "TimeTester" in "Register_Accountname"
    And I type "TimoTester" in "Register_Displayname"
    And I type "test123!" in "Register_Password"
    And I type "test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Existing Accountname"

  Scenario: existing Displayname
    Given I am on the login view
    When I type "1651864sadgasg" in "Register_Accountname"
    And I type "Geri" in "Register_Displayname"
    And I type "test123!" in "Register_Password"
    And I type "test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Existing Displayname"
