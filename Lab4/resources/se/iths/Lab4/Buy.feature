Feature: Continue checkout

Scenario: Continue checkout with correct billing info 
Given I added "Magic Mouse" to cart
When I continue and fill in the correct billing details
And I go on with purchase
Then I would get a message with Thank you

Scenario: Trying to buy without correct billing info
Given I added "Magic Mouse" to cart
When I continue and fill in billing details
But I go on with purchase without email
Then I will return to checkout
And when I continue again I would see the message Please enter a valid email.
 