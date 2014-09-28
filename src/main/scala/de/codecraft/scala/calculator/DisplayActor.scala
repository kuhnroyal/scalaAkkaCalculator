package de.codecraft.scala.calculator

import akka.actor.{ActorLogging, ActorRef, Props}
import macroid.akkafragments.FragmentActor

object DisplayActor {
  case class Display(value: String)
  case object Clear

  def props = Props(new DisplayActor)
}

class DisplayActor extends FragmentActor[DisplayFragment] with ActorLogging {
  import de.codecraft.scala.calculator.DisplayActor._
  import macroid.akkafragments.FragmentActor._

  def receive = receiveUi andThen {
    case Clear => ???

    case Display(v) => ???

    case AttachUi(_) =>

    case DetachUi =>
      // do nothing
  }
}
