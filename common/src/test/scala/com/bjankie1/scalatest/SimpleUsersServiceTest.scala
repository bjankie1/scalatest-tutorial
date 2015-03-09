package com.bjankie1.scalatest

import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.{Matchers, fixture}

class SimpleUsersServiceTest extends fixture.FlatSpec with Matchers with ScalaFutures with PatienceConfiguration
  with RemoteClientFixture {

  it should "return a positive integer within 150ms" in { client =>
    val service = new SimpleUsersService(client)
    val result = service.count()
    whenReady(result) { count =>
      count should be >= 0
    }
  }


  it should "load a person within 150ms" in { client =>
    val service = new SimpleUsersService(client)
    val result = service.load("SOMEONE")
    whenReady(result) { person =>
      person should have (
        'id ("SOMEONE")
      )
    }
  }
}
