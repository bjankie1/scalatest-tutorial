package com.bjankie1.scalatest

import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

trait RemoteClientFixture { this: fixture.Suite =>

  type FixtureParam = RemoteClient

  class FakeClient extends RemoteClient {
    override def loadUser( id: String ): Future[(String, String)] =
      Future((Random.nextString(20), Sex.values.toList(Random.nextInt(1)).toString))
  }

  def withFixture(test: OneArgTest): Outcome = {
    try {
      val client = new FakeClient
      withFixture(test.toNoArgTest(client))
    }
  }

}
