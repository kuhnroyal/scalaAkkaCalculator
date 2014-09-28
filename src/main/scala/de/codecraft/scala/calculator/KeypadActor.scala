package de.codecraft.scala.calculator

import akka.actor.{ActorLogging, Props}
import macroid.akkafragments.FragmentActor

object KeypadActor {
  def props = Props(new KeypadActor)
}

class KeypadActor extends FragmentActor[KeypadFragment] with ActorLogging {
  import CalculatorActor._

  lazy val calculator = context.system.actorSelection("/user/calculate")

  def receive = receiveUi andThen {
    case m: Message => calculator ! m
  }
}