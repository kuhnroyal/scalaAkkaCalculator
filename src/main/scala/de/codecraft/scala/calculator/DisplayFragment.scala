package de.codecraft.scala.calculator

import android.view.{ViewGroup, LayoutInflater}
import android.os.Bundle
import android.widget.TextView

import macroid._
import macroid.FullDsl._
import macroid.akkafragments.AkkaFragment
import macroid.util.Ui

class DisplayFragment extends AkkaFragment with Contexts[AkkaFragment] {
  import de.codecraft.scala.calculator.DisplayActor._

  lazy val actorName = getArguments.getString("name")

  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  var result = slot[TextView]

  def receive : Ui[Any] = {
    case Clear => result <~ text("")
    case Display(v) => result <~ text(v)
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle) = getUi {
    w[TextView] <~ wire(result)
  }
}
