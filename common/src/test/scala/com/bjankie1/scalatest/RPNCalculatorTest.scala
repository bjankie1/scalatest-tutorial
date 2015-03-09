package com.bjankie1.scalatest

import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, FlatSpec}

import scala.math.BigDecimal.double2bigDecimal

class RPNCalculatorTest extends FlatSpec with Matchers with PropertyChecks {

  it should "push and pop" in {
    var calculator = new RPNCalculator()
    calculator = calculator push 1
    val value = calculator.drop
    value.stack shouldBe empty
  }

  it should "build a stack for any set of numbers" in {
    forAll { (input: List[Double]) =>
      val calc = input.foldLeft(new RPNCalculator)((c,n) => c push n)
      calc.stack shouldBe input.reverse
    }
  }

  it should "push should add to the stack" in {
    var calculator = new RPNCalculator
    calculator = calculator push 666
    calculator.stack should not be empty
    calculator.stack should contain only 666
  }

  it should "return None when popping from empty stack" in {
    val calculator = new RPNCalculator
    an [UnsupportedOperationException] should be thrownBy calculator.drop
  }


}

