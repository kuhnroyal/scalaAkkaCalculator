package de.codecraft.scala.calculator

import akka.actor.{ActorLogging, Props}
import macroid.akkafragments.FragmentActor

object DisplayActor {
  case class Display(value: String)
  case object Clear

  def props = Props(new DisplayActor)
}

class DisplayActor extends FragmentActor[DisplayFragment] with ActorLogging {
  import de.codecraft.scala.calculator.DisplayActor._

  def receive = receiveUi andThen {
    case Clear =>
      log.debug("received clear message")
      withUi(f => f.clear)

    case Display(v) =>
      log.debug(s"received display string: ${v}")
      withUi(f => f.display(v))
  }
}
