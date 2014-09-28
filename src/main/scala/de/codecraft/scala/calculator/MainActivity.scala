package de.codecraft.scala.calculator

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.ViewGroup.LayoutParams._
import android.widget.LinearLayout

// import macroid stuff
import macroid.FullDsl._
import macroid._
import macroid.akkafragments.AkkaActivity

/** The main activity */
class MainActivity extends FragmentActivity with Contexts[FragmentActivity] with IdGeneration with AkkaActivity {
  // name of our actor system
  val actorSystemName = "calculator"

  // fragments
  lazy val calculate = actorSystem.actorOf(CalculatorActor.props, "calculate")
  lazy val display = actorSystem.actorOf(DisplayActor.props, "display")
  lazy val keypad = actorSystem.actorOf(KeypadActor.props, "keypad")

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    // initialize the actors
    calculate

    // layout params
    val lps = lp[LinearLayout](MATCH_PARENT, WRAP_CONTENT, 1.0f)

    // include the two fragments
    val view = l[LinearLayout](
      // we pass a name for the actor, and id+tag for the fragment
      f[DisplayFragment].pass("name" -> "display").framed(Id.ping, Tag.ping) <~ lps,
      f[KeypadFragment].pass("name" ->"keypad").framed(Id.pong, Tag.pong) <~ lps
    ) <~ vertical

    setContentView(getUi(view))
  }

  override def onStart() = {
    super.onStart()
  }
}
