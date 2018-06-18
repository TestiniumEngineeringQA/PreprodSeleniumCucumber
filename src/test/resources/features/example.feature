Feature: Online Process 

@goToWebsite
Scenario: Website Test Scenario
	Given Fullscreen to window
	Given Go to "http://www.google.com"
	When Wait "3" seconds
	Then Go to "http://www.hepsiburada.com"
	Given Wait "3" seconds
	* Wait all request complete
	* Wait jquery request complete
	* Wait page load complete
	* Wait "3000" ms
