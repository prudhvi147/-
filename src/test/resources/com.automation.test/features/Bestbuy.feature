Feature:  BestBuy Add a product to Cart

  Scenario:  Add Iphone's To cart
    Given Launch Best Buy Url In Edge
    And User Searches for "Apple - Pre-Owned iPhone 13 Pro 5G 128GB (Unlocked) - Graphite" product
    When User adds the product to Cart
    And User continues Shopping
    And User Searches for "Apple - Pre-Owned iPhone 12 5G 64GB (Unlocked) - Black" product
    And User adds the product to Cart
    When User redirects To Checkout page
    Then User validates checkout page is displayed as expected

  Scenario Outline:  Add Iphone's To cart - With parameterization
    Given Launch Best Buy Url In Edge
    And User Searches for "<Name 1>" product
    When User adds the product to Cart
    And User continues Shopping
    And User Searches for "<Name 2>" product
    And User adds the product to Cart
    When User redirects To Checkout page
    Then User validates checkout page is displayed as expected
    Examples:
    |Name 1 |Name 2|
    |Apple - Pre-Owned iPhone 13 Pro 5G 128GB (Unlocked) - Graphite|Apple - Pre-Owned iPhone 12 5G 64GB (Unlocked) - Black|
