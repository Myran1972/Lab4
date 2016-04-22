Feature: My Account

Scenario: Register without answer to security question
  Given I have opened the registration form
  When I registrer username "orimligt" 
  And I register email "felaktig.orimlig@gmail.com"
  But I Login without an answer to the equation	
  Then I will receive an error message like "ERROR: Your answer was incorrect - please try again."


Scenario Outline: Login Success and Failure
Given I have opened My account
When I fill in my username "<username>"
And I fill in my password "<password>"
And I Login
Then I should get result that "<username>" logged in "<status>"
	
Examples:
	|username|password|status|
	|marrun|HejPaDig!KlAr10|successful|
	|invalid|invalidPassWord|unsuccessful|
