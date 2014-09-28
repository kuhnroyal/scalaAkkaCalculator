package de.codecraft.scala.calculator

import akka.actor.{Props, Actor}

object CalculatorActor {
  def props = Props(new CalculatorActor)

  // Operations
  abstract class Message(val symbol: String)
  abstract class Operation(symbol: String) extends Message(symbol)
  case object Addition extends Operation("+")
  case object Subtraction extends Operation("-")
  case object Multiplication extends Operation("*")
  case object Division extends Operation("/")
  case object Equals extends Operation("=")
  case object Clear extends Operation("C")

  // Numbers
  abstract class Number(symbol: String, val value: Int) extends Message(symbol)
  case object One extends Number("1", 1)
  case object Two extends Number("2", 2)
  case object Three extends Number("3", 3)
  case object Four extends Number("4", 4)
  case object Five extends Number("5", 5)
  case object Six extends Number("6", 6)
  case object Seven extends Number("7", 7)
  case object Eight extends Number("8", 8)
  case object Nine extends Number("9", 9)
  case object Zero extends Number("0", 0)
}

class CalculatorActor extends Actor {
  import de.codecraft.scala.calculator.CalculatorActor._
  import de.codecraft.scala.calculator.DisplayActor._

  lazy val display = context.system.actorSelection("/user/display")

  override def receive: Receive = {
    case n: Number => display ! Display(n.symbol)
    case CalculatorActor.Clear => display ! DisplayActor.Clear
    case o: Operation => display ! Display(o.symbol)
  }
}
