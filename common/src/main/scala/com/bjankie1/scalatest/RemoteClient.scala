package com.bjankie1.scalatest

import scala.concurrent.Future

trait RemoteClient {

  def loadUser(id: String): Future[(String, String)]

}
