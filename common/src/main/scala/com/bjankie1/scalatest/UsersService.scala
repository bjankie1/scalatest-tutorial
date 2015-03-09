package com.bjankie1.scalatest

import scala.concurrent.Future

trait UsersService {

  def count(): Future[Int]

  def load(id: String): Future[Person]

}

object Sex extends Enumeration {
  val Male, Female = Value
}

case class Person(id: String, name: String, sex: Sex.Value)
