Feature: Add shopping items to wish list

  @AddItemsToWishList
  Scenario: Adding item to Wish list
    Given application is launched
    And I am logged in with username "amit3@automation.com" and password "password"
    Given I am in Landing page
    When I click on BEST SELLERS button
    And I select first item from list
    And item description and condition is displayed
    And I set size to L
    And I set color to Green
    And I click on Add to wishlist button
    And I open My Whishlist page
    And I click on My Wishlist (the one automatically created)
    Then My Wishlist item are visible
    And correct wishlist item preferences is displayed