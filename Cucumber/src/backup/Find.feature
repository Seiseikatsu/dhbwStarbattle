Feature: You can find a game

Scenario: Find a game
    Given another application is in queue
    And I am logged in as "TimoTester" with pw "Timotest#1" 
    And I am on the lobby view
    When I click on "Play"
    And I am on the chooseGame view
    Then I see the gameclient
    
Scenario: Dodge the queue
	Given I am logged in as "TimoTester" with pw "Timotest#1" 
    And I am on the lobby view
    When I click on "Play"
    And I am on the chooseGame view
    And I click on "Cancel"
    Then I am on the chooseGame view