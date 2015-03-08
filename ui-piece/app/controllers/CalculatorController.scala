package controllers

import com.bjankie1.scalatest.RPNCalculator
import play.api.Logger
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{ Request, AnyContent, Action, Controller }

import scala.util.Try

object CalculatorController extends Controller {

  case class RPNCalculatorPush(rPNCalculator: RPNCalculator, value: BigDecimal)

  implicit val calcForm: Form[(Option[BigDecimal], RPNCalculator)] = Form(
    mapping(
      "value" -> optional(bigDecimal),
      "stack" -> list(bigDecimal)
    )((v, s) => (v, RPNCalculator(s)))(v => Some((v._1, v._2.stack)))
  )

  def index = Action {
    Ok(views.html.calc(calcForm.fill((None, RPNCalculator()))))
  }

  def push = Action { implicit request =>
    calcForm.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.calc(formWithErrors)),
      {
        case (Some(value), c) =>
          Logger.info("Nowa argument na stosie")
          val newCalc = c.push(value)
          Logger.info(s"Stos: ${newCalc.stack}")
          Ok(views.html.calc(calcForm.fill(None, newCalc)))
      }
    )
  }

  def operation(f: RPNCalculator => RPNCalculator)(implicit request: Request[AnyContent]) = {
    calcForm.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.calc(formWithErrors)),
      {
        case (_, c @ RPNCalculator(stack)) =>
          Try {
            Ok(views.html.calc(calcForm.fill(None, f(c))))
          }.getOrElse {
            BadRequest(views.html.calc(calcForm.fill((None, RPNCalculator())).withGlobalError("stack.too.short")))
          }
      }
    )
  }

  def drop = Action { implicit request =>
    Logger.info("Zrzucam argument ze stosu")
    operation(_.drop)
  }

  def add = Action { implicit request =>
    Logger.info("Dodaje liczby na stosie")
    operation(_.+)
  }

  def subtract = Action { implicit request =>
    Logger.info("Odejmuje liczby na stosie")
    operation(_.-)
  }

}
