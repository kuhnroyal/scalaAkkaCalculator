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
    m match {
      case One => w[Button] <~ text("1") <~ On.click(Ui(send(m)))
      case Two => w[Button] <~ text("2") <~ On.click(Ui(send(m)))
      case Three => w[Button] <~ text("3") <~ On.click(Ui(send(m)))
      case Four => w[Button] <~ text("4") <~ On.click(Ui(send(m)))
      case Five => w[Button] <~ text("5") <~ On.click(Ui(send(m)))
      case Six => w[Button] <~ text("6") <~ On.click(Ui(send(m)))
      case Seven => w[Button] <~ text("7") <~ On.click(Ui(send(m)))
      case Eight => w[Button] <~ text("8") <~ On.click(Ui(send(m)))
      case Nine => w[Button] <~ text("9") <~ On.click(Ui(send(m)))
      case Zero => w[Button] <~ text("0") <~ On.click(Ui(send(m)))
      case Clear => w[Button] <~ text("C") <~ On.click(Ui(send(m)))
      case Addition => w[Button] <~ text("+") <~ On.click(Ui(send(m)))
      case Subtraction => w[Button] <~ text("-") <~ On.click(Ui(send(m)))
      case Multiplication => w[Button] <~ text("*") <~ On.click(Ui(send(m)))
      case Division => w[Button] <~ text("/") <~ On.click(Ui(send(m)))
      case Equals => w[Button] <~ text("=") <~ On.click(Ui(send(m)))
    }
  }

  def layout(implicit ctx: ActivityContext) = {
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
