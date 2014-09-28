package de.codecraft.scala.calculator

import android.os.Bundle
import android.view.{ViewGroup, LayoutInflater}
import android.widget.{FrameLayout, Button}
import macroid.Contexts
import macroid.FullDsl._
import macroid.akkafragments.AkkaFragment


class KeypadFragment extends AkkaFragment with Contexts[AkkaFragment] {
  // get actor name from arguments
  lazy val actorName = getArguments.getString("name")

  // actor for this fragment
  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  // a slot for the racket button
  var racket = slot[Button]

  // trigger the fadeIn effect
  def receive = ???


  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle) = getUi {
    l[FrameLayout](
      w[Button] <~ wire(racket) <~ On.click()
    )
  }
}
