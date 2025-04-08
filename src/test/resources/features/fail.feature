Feature: success

  @success
  Scenario: success
    Given Go to "https://www.amazon.com.tr/"
    Then Wait 2 seconds
    Then Click to element "random"
    Then Wait 2 seconds
    Then Click to element "amazonlogo"
    Then Wait 2 seconds
    Then Click to element "konumugüncelle"
    Then Wait 2 seconds
    Then Click to element "closebutton"
    Then Wait 2 seconds

  @failll
  Scenario: failll
    Given Go to "https://www.amazon.com.tr/"
    Then Wait 2 seconds
    Then Click to element "randommm"