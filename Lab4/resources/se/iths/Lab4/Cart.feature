Feature: Add, remove and change items in cart

Scenario: Add one item to cart
Given I added "iPod Nano Blue" to cart
When I go to checkout
Then the checkout will show 1 "iPod Nano Blue"


Scenario Outline: Add three items to cart and remove one
Given I add "<item1>" to cart
And I add "<item2>" to cart
And I added "<item3>" to cart
When I go to checkout
And I remove "<item2>"
Then I would see that the quantity next to the cart equals 2

Examples:
|item1|item2|item3|
|Magic Mouse|iPhone 5|Apple iPod touch Large|


Scenario: Change the quantity
Given I added "iPod Nano Blue" to cart
When I change the quantity to "2" in checkout
Then I would see that the quantity next to the cart equals 2