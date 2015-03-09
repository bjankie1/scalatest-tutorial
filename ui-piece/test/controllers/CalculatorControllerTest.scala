package controllers

import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.scalatest.selenium.HtmlUnit
import org.scalatest.{FlatSpec, Matchers, Tag}

object UITest extends Tag("com.bjankie1.scalatest.SeleniumTests")

class CalculatorControllerTest extends FlatSpec with Matchers with HtmlUnit with Eventually with IntegrationPatience {

  val host = "http://localhost:9000/"

  it should "have the correct title" taggedAs UITest in {
    go to host
    pageTitle should be("Kalkulator RPN")
  }

  it should "push new value to the stack" taggedAs UITest  in {
    go to host
    textField("value").value = "1.0"
    click on id("push")
    eventually {
      textField(id("stack_0")).value should be("1.0")
    }
  }

  it should "execute adding operation" taggedAs UITest in {
    go to host

    textField("value").value = "1.0"
    click on "push"
    eventually {
      textField(id("stack_0")).value should be("1.0")
    }

    textField("value").value = "2.0"
    click on "push"
    eventually {
      textField(id("stack_0")).value should be("2.0")
    }

    click on "add"
    eventually {
      val source = webDriver.getPageSource
      source should not contain "Execution exception"
      textField(id("stack_0")).value should be("3.0")
    }
  }

}
