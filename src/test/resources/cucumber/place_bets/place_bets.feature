@Feature_place_bets @place_bets
Feature: Place Bets

  Background:
    Given I go to the 'Sports Betting' page

    Scenario: Add bet on English Premier League event to bet slip
      And I navigate to the 'Football' 'Competitions' page
      When I go to an English Premier League event
      And I add a '£0.05' bet for the 'home' team to 'win'
      Then the selected bet should be added to my bet slip

      Scenario: Place bet on English Premier League event
        * I am logged in
        And I go to Football Competitions page
        When I go to an English Premier League event
        And I place a bet a '£0.05' bet for the 'home' team to 'win'
        Then the return offered is based on the correct odds