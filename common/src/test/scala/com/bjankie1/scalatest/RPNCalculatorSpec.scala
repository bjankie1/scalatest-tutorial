package com.bjankie1.scalatest

import org.scalatest.{ Matchers, GivenWhenThen, FeatureSpec }

class RPNCalculatorSpec extends FeatureSpec with GivenWhenThen with Matchers {

  info("As a scientist")
  info("I want to be able to add a few numbers")
  info("So that I can win a Nobel prize")
  info("And destroy the world")

  feature("RPN Calculator") {
    scenario("Adding two positive numbers") {
      Given("Fresh calculator")
      val calculator = new RPNCalculator()
      When("push two numbers")
      val calculator1 = calculator.push(1.0).push(2.0)

      Then("Sum of the numbers should be 3.0")
      val result = calculator1.+
      assert(result.stack.head == 3.0)
    }
  }

}
