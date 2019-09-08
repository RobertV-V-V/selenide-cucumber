Feature: Craiglist sorting functionality

  Background:
    Given I open browser with helsinki.craiglist home page
    And I set english language
    And I open "all housing" category

  Scenario: User can sort the grid by lowest price
    When I sort items by lowest_price
    Then I see that items are sorted by lowest_price

  Scenario: User can sort the grid by highest price
    When I sort items by highest_price
    Then I see that items are sorted by highest_price

  Scenario: User has set of sorting options by default
    When I open sorting selector
    Then I see that lowest_price sorting option is available
    And I see that highest_price sorting option is available
    And I see that newest sorting option is available

  Scenario: User has set of sorting options after the search
    When I apply search
    And I open sorting selector
    And I see that upcoming sorting option is available
    And I see that newest sorting option is available
    And I see that relevant sorting option is available
    Then I see that lowest_price sorting option is available
    And I see that highest_price sorting option is available