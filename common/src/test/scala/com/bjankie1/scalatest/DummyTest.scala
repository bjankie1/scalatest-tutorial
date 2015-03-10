package com.bjankie1.scalatest

import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, Checkpoints, FunSuite}

class DummyTest extends FunSuite with Checkpoints with Matchers with PropertyChecks {

  test("Dummy should greet everyone") {
    forAll { ( name: String ) =>
      val dummy = new Dummy
      val greeting = dummy.sayHello(name)
      val cp = new Checkpoint
      cp { greeting should startWith("dingo") }
      cp { greeting should endWith("Hello") }
      cp { greeting should (startWith("Hello") and include(name)) }
      cp.reportAll()
    }
  }

}
