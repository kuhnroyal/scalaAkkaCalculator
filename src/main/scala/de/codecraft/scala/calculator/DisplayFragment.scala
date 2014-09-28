package de.codecraft.scala.calculator

import android.view.{ViewGroup, LayoutInflater}
import android.os.Bundle
import android.widget.{LinearLayout, TextView}
import android.view.ViewGroup.LayoutParams._

import macroid._
import macroid.FullDsl._
import macroid.akkafragments.AkkaFragment

class DisplayFragment extends AkkaFragment with Contexts[AkkaFragment] {

  lazy val actorName = getArguments.getString("name")

  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  var result = slot[TextView]

  def clear = result <~ text("")

  def display(s: String) = result <~ text(s)

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle) = getUi {
    l[LinearLayout]( w[TextView] <~ wire(result)) <~ layoutParams[LinearLayout](MATCH_PARENT, WRAP_CONTENT)
  }
}
