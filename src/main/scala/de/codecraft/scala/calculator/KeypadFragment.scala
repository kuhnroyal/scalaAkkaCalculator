package de.codecraft.scala.calculator

import android.os.Bundle
import android.view.{ViewGroup, LayoutInflater}
import android.widget.{LinearLayout, Button}
import macroid.util.Ui
import macroid.{ActivityContext, Contexts}
import macroid.FullDsl._
import macroid.akkafragments.AkkaFragment
import CalculatorActor._


class KeypadFragment extends AkkaFragment with Contexts[AkkaFragment] {
  // get actor name from arguments
  lazy val actorName = getArguments.getString("name")

  // actor for this fragment
  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  // a slot for the racket button
  var racket = slot[Button]

  //
  def receive = {}

  def send(m: CalculatorActor.Message) = actor.foreach(_ ! m)

  def addButton(m: CalculatorActor.Message) = {
    w[Button] <~ text(m.symbol) <~ On.click(Ui(send(m)))
  }

  def layout = {
    l[LinearLayout](
      l[LinearLayout](
        addButton(One),
        addButton(Two),
        addButton(Three),
        addButton(Addition)
      ) <~ horizontal,
      l[LinearLayout](
        addButton(Four),
        addButton(Five),
        addButton(Six),
        addButton(Subtraction)
      ) <~ horizontal,
      l[LinearLayout](
        addButton(Seven),
        addButton(Eight),
        addButton(Nine),
        addButton(Multiplication)
      ) <~ horizontal,
      l[LinearLayout](
        addButton(Zero),
        addButton(Clear),
        addButton(Equals),
        addButton(Division)
      ) <~ horizontal
    ) <~ vertical
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle) = getUi {
    layout
  }
}
