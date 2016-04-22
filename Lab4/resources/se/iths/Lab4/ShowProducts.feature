Feature: Show item among others

Scenario: Show all products and find the item among them
Given I am on the home page
When I open All products
Then I would see the "iPhone 5" among the products


Scenario: If I choose a category I will find item in that category
Given I am on the home page
When I choose the "iPhones" in Product category
Then I would see the "iPhone 5" among the products
