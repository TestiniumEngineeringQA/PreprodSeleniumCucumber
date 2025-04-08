Feature: example

  @example
  Scenario: example
    Given Go to "https://www.amazon.com.tr/"
    Then Click to element "random"
    Then Wait 3 seconds


    @Success
    Scenario: Success
      Given Go to "https://www.amazon.com.tr/"
      Then Wait 2 seconds
      Then Click to element "nav-logo-sprites"
      Then Wait 2 seconds
      Then Click to element "nav-global-location-popover-link"
      Then Wait 2 seconds


