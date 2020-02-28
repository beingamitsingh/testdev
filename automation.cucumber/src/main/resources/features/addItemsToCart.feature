Feature: Add shopping items to cart for purchase

  @AddItemsToCart
  Scenario: Adding item to the cart
    Given application is launched
    And I am logged in
    Given I am in Landing page
    When I select Search menu
    And I enter "Blouse"
    And I click on Search button
    And only 1 result is found
    And I select the item
    And item description and condition is displayed
    And I change quantity to 2
    And I set size to S
    And I set color to White
    And I select Add to cart button
    And item is successfully added to cart
    And I click on Proceed to checkout button
    Then Shopping cart summary page is opened
    And correct description is specified
    And amount is correctly calculated
    And Proceed to checkout button is visible