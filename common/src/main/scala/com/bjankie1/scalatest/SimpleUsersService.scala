package com.bjankie1.scalatest

import scala.concurrent.Future
import scala.util.Random

import scala.concurrent.ExecutionContext.Implicits.global

class SimpleUsersService(remoteClient: RemoteClient) extends UsersService {

  def count(): Future[Int] = Future{ Thread.sleep(10);Random.nextInt(100) }

  override def load( id: String ): Future[Person] = {
    remoteClient.loadUser(id).map { case (name, sex) =>
      Person(id, name, Sex.withName(sex))
    }
  }
}
