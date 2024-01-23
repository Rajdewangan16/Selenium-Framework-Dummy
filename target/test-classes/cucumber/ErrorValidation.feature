
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
  	Given I landed on Ecommerce Page 
    When Logged in with username "<username>" and password "<password>"
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  							| password 		|
      | anshu1234fh@gmail.com 		| A90nshu@1234 	|
