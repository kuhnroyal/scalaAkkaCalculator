package de.codecraft.scala.calculator

import akka.actor.{Props, Actor}

object CalculatorActor {
  def props = Props(new CalculatorActor)

  // Operations
  abstract class Message
  abstract class Operation extends Message
  case object Addition extends Operation
  case object Subtraction extends Operation
  case object Multiplication extends Operation
  case object Division extends Operation
  case object Equals extends Operation
  case object Clear extends Operation

  // Numbers
  abstract class Number extends Message
  case object One extends Number
  case object Two extends Number
  case object Three extends Number
  case object Four extends Number
  case object Five extends Number
  case object Six extends Number
  case object Seven extends Number
  case object Eight extends Number
  case object Nine extends Number
  case object Zero extends Number
}

class CalculatorActor extends Actor {
  import de.codecraft.scala.calculator.CalculatorActor._

  lazy val display = context.system.actorSelection("/user/display")

  override def receive: Receive = {
    case n: Number => display ! n
    case o: Operation => display ! o
  }
}
