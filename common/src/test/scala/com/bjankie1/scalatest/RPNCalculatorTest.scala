package com.bjankie1.scalatest

import org.scalatest.{Matchers, FlatSpec}

import scala.math.BigDecimal.double2bigDecimal

class RPNCalculatorTest extends FlatSpec with Matchers {

  it should "push and pop" in {
    var calculator = new RPNCalculator()
    calculator = calculator.push(1.0)
    val value = calculator.drop
    value.stack shouldBe empty
  }


  it should "push should add to the stack" in {
    var calculator = new RPNCalculator()
    calculator = calculator.push(1.0)
    calculator.stack should not be empty
    calculator.stack should contain only 1.0
  }

  it should "return None when popping from empty stack" in {
    val calculator = new RPNCalculator()
    an [IndexOutOfBoundsException] should be thrownBy calculator.drop
  }


}

