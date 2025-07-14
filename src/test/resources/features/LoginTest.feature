Feature:Login functionality for SauceDemo users

  Background:
    Given I open the SauceDemo login page

  @auth @smoke @regression
  Scenario Outline: Verify login with multiple types of users

    When I login with username "<username>" and password "<password>"
    Then I should "<result>" see the inventory page
    And I should "<errorVisibility>" see the error message "<expectedError>"

    Examples:
      | username                | password     | result | errorVisibility | expectedError                                       |
      | standard_user           | secret_sauce |        | not             |                                                     |
      | locked_out_user         | secret_sauce | not    | yes             | Epic sadface: Sorry, this user has been locked out. |
      | problem_user            | secret_sauce |        | not             |                                                     |
      | performance_glitch_user | secret_sauce |        | not             |                                                     |
