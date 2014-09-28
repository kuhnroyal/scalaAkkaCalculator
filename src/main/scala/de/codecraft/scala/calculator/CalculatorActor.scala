package de.codecraft.scala.calculator

import akka.actor.{Props, Actor}

object RacketActor {
  def props = Props(new CalculatorActor)
}

class CalculatorActor extends Actor {

  override def receive: Receive = ???
}
