Feature: example

  @example
  Scenario: example
    Given Go to "https://www.amazon.com.tr/"
    Then Wait 2 seconds
    Then Click to element "random"
    Then Wait 2 seconds
    Then Click to element "amazonlogo"
    Then Wait 2 seconds
    Then Click to element "konumugüncelle"
    Then Wait 2 seconds
    Then Write text "bilgisayar" to "searchbuttontext" element
    Then Wait 2 seconds
    Then Click to element "searchbutton"


