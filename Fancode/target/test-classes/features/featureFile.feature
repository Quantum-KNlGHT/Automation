
@fancode_city
Feature: Title of your feature
  I want to use this template for my feature file

  @test1
  Scenario:  All the users of City `FanCode` should have more than half of their todos task completed
    Given User has the todo tasks
    And User belongs to the city FanCode
 		Then User Completed task percentage should be greater than 50%