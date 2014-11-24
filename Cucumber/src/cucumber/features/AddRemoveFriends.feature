Feature: You can add and remove friends from your friendlist

  Scenario: Send friend request
    Given I am logged in as "TimoTester"
    When I click on button "Add friend"
    And I type "Hans"
    And I click on button "send request"
    Then I am on the lobby view
    And List counter "Request sent" increased by 1

Scenario: Accept friend request
    Given I am logged in as "TimoTester"
    When I expand list "Friend requests"
    And I click on "accept" on list item "Jürgen Jürgens"
    Then I am on the lobby view
    And List counter "Friend requests" decreased by 1
    
