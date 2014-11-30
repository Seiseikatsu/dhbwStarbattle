Feature: You can open chat with your friends

Scenario: Send Message
    Given another application is logged in with "Accname" and "Password"
    And I am logged in as "TimoTester" with pw "Timotest#1" 
    And I am on the lobby view
    When I expand list "Online"
    And I click on "chat" on list item "Displayname"
    And I type "test" in chat "Displayname"
    And I click on button "Send" in chat "Displayname"
    Then I see "test" in the chat "Displayname"
    And another application sees "test" in the chat "Timo"