package com.bjankie1.scalatest

import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ FlatSpec, Matchers }
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class UsersServiceMockTest extends FlatSpec with MockFactory with Matchers with ScalaFutures {

  it should "load a user" in {
    val clientMock = mock[RemoteClient]
    (clientMock.loadUser(_: String)).expects("1").returns(Future(("tom", Sex.Male.toString)))
    val service = new SimpleUsersService(clientMock)
    val result = service.load("1")
    whenReady(result) { person =>
      person should have(
        'id("1"),
        'name("tom")
      )
    }
  }

}
