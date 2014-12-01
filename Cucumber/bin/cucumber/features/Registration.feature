Feature: Registration is working if there are no errors



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