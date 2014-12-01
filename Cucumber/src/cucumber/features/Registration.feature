Feature: Registration is working if there are no errors

  Scenario: normal registration
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "HansTester" in "Register_Accountname"
    And I type "SuperHans" in "Register_Displayname"
    And I type "Test123!" in "Register_Password"
    And I type "Test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the lobby view
    And I delete account "HansTester"

  Scenario: existing Accountname
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "TimoTester" in "Register_Accountname"
    And I type "fewjrwjelrjwkljfcsdfsdf" in "Register_Displayname"
    And I type "Test123!" in "Register_Password"
    And I type "Test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Existing Accountname"

  Scenario: invalid Accountname
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "p" in "Register_Accountname"
    And I type "werjkljsdlfjwernhwrjkr" in "Register_Displayname"
    And I type "Test123!" in "Register_Password"
    And I type "Test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Invalid Accountname"

  Scenario: existing Displayname
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "dsfsfweqgwetw" in "Register_Accountname"
    And I type "Geri" in "Register_Displayname"
    And I type "Test123!" in "Register_Password"
    And I type "Test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Existing Displayname"

  Scenario: invalid Displayname
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "sdfsdfwerwemrwe" in "Register_Accountname"
    And I type "p" in "Register_Displayname"
    And I type "Test123!" in "Register_Password"
    And I type "Test123!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Invalid Displayname"

  Scenario: invalid Password
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "sdfsdfwerwemrwe" in "Register_Accountname"
    And I type "pjfdlafjfaf" in "Register_Displayname"
    And I type "T" in "Register_Password"
    And I type "T" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Invalid Password"

  Scenario: Passwords do not match
    Given I am on the login view
    When I click on button "Button_Register"
    When I type "sdfsdfwerwemrwe" in "Register_Accountname"
    And I type "pjfdlafjfaf" in "Register_Displayname"
    And I type "Tf1!" in "Register_Password"
    And I type "Tfjasdkf1!" in "Register_Repeat_Password"
    And I type "stiff@stiff.de" in "Register_Email"
    And I click on button "Register_Button"
    Then I am on the register view
    And I receive an error message saying "Passwords don't match"
