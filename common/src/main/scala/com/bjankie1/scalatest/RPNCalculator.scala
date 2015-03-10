package com.bjankie1.scalatest

case class RPNCalculator(stack: List[BigDecimal] = Nil) {

  def push(number: BigDecimal) = copy(number :: stack)

  def drop = copy(stack.tail)

  def clear = copy(Nil)

  def + : RPNCalculator = copy(stack.take(2).sum :: stack.drop(2))

  def - : RPNCalculator = copy(stack.take(2).reduce(_ - _) :: stack.drop(2))

  def * : RPNCalculator = copy(stack.take(2).product :: stack.drop(2))

  def / : RPNCalculator = copy(stack.take(2).reduce(_ / _) :: stack.drop(2))

  def sqrt : RPNCalculator = copy(BigDecimal(math.sqrt(stack.head.toDouble)) :: stack.tail)

}
