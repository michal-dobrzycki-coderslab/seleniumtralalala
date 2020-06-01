Feature: Google multi search search

  Scenario Outline: user can search any keyword
    Given an open browser with google.com
    When a keyword <searchText> is entered in input field
    Then the first one should contain <searchText>
    And close browser

    Examples:
    | searchText         |
    | selenium           |
    | Mandaryna          |
    | Robert Lewandowski |