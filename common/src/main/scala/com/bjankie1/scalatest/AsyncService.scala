package com.bjankie1.scalatest

import scala.concurrent.Future
import scala.util.Random

import scala.concurrent.ExecutionContext.Implicits.global

class AsyncService {

  def count(): Future[Int] = Future{ Thread.sleep(10);Random.nextInt(100) }

}
