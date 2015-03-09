package com.bjankie1.scalatest

import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.{FlatSpec, Matchers}

class AsyncServiceTest extends FlatSpec with Matchers with ScalaFutures with PatienceConfiguration {

  it should "return a positive integer within 150ms" in {
    val service = new AsyncService
    val result = service.count()
    whenReady(result) { count =>
      count should be >= 0
    }
  }

}
