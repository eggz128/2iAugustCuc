@Run @GUI
Feature: Google search

  Scenario: Search google for edgewords
    Given I am on the GoOGle Homepage
    When I search for "ITV"
    Then "ITV" is the top result
  @Ignore
  Scenario Outline: Search google for stuff
    Given I am on the GoOGle Homepage
    When I search for "<searchTerm>"
    Then "<searchTerm>" is the top result
    Examples:
      | searchTerm |
      | Edgewords  |
      | BBC        |
      | ITV        |

  Scenario: Inline table
    Given I am on the Google search page
    When I search for Edgewords
    Then I see in the results
      | url                                 | title                                                    |
      | https://www.edgewordstraining.co.uk | Edgewords Training - Automated Software Testing Training |
      | https://twitter.com › edgewords     | Edgewords - Twitter                                      |


