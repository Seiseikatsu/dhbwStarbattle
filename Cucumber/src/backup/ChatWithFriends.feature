Feature: You can open chat with your friends

Scenario: Send Message
    Given I am logged in as "TimoTester" with pw "Timotest#1"
    And I am on the lobby view
    When I expand list "Online"
    And I click on "chat" on list item "Hans"
    And I click on "eingabe" in the chat "Hans"
    And I type "test"
    And I click on button "Send"
    Then I see "test" in the chat "Hans"