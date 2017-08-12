@Feature_place_bets @place_bets
Feature: Place Bets

  Background:
    Given I go to the 'Sports Betting' page

  Scenario Outline: Add bet on English Premier League event to bet slip
    And I navigate to the 'Football' 'Competitions' page
    When I navigate to an 'English Premier League' football event
    And I add a £<amount> bet for <side>
    Then the selected bet should be added to my bet slip
    And the return offered is based on the correct odds
    Examples:
      | side | amount |
      | home | 0.05   |
      | draw | 13.37 |
      | away | 100.52 |