
@tag
Feature: Purchase the order from Ecommerce website 
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page 

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username "<username>" and password "<password>"
    When I add product "<productName>" to cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | username  							| password 		| productName |
      | anshu1234@gmail.com 		| Anshu@1234 	| ZARA COAT 3 |
