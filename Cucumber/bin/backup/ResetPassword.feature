Feature: You can reset your password in case you forgot it

  Scenario: Reset Password & Login
    Given I am on the login view
    When I click on button "Button_ForgotPassword"
    And I type "Accname" in "ForgotPassword_Accname"
    And I type "EMail@web.de" in "ForgotPassword_Email"
    And I click on button "ForgotPassword_Send"
    And I get password and do login
    Then I am on the lobby view
