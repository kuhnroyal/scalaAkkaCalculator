package de.codecraft.scala.calculator

import akka.actor.{Props, Actor}

object CalculatorActor {
  def props = Props(new CalculatorActor)

  // Operations
  case object Addition
  case object Subtraction
  case object Multiplication
  case object Division
  case object Equals
  case object Clear

  // Numbers
  case object One
  case object Two
  case object Three
  case object Four
  case object Five
  case object Six
  case object Seven
  case object Eight
  case object Nine
  case object Zero
}

class CalculatorActor extends Actor {

  override def receive: Receive = ???
}
