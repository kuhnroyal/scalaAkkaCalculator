package de.codecraft.scala.calculator

import akka.actor.{ActorLogging, Props}
import macroid.akkafragments.FragmentActor

object KeypadActor {
  def props = Props(new KeypadActor)
}

class KeypadActor extends FragmentActor[KeypadFragment] with ActorLogging {
  import macroid.akkafragments.FragmentActor._

  def receive = receiveUi andThen {
    case AttachUi(_) =>

    case DetachUi =>
    // do nothing
  }
}