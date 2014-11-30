Feature: You can add and remove friends from your friendlist

  Scenario: Send friend request
    Given I am logged in as "TimoTester" with password "TimoTest#1"
    When I click on button "Add_Friend"
    And I type "Displayname" in "Friendname"
    And I click on button "Send_Request"
    Then I am on the lobby view
    And I see a friendrequest for "Displayname"

  Scenario: Accept friend request
    Given I am logged in as "Accname" with password "Password"
    Then I am on the lobby view
    And I see a incoming friendrequest from "Timo"
