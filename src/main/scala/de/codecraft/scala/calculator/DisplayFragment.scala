package de.codecraft.scala.calculator

import android.view.{Gravity, ViewGroup, LayoutInflater}
import android.os.Bundle
import android.widget.{FrameLayout, Button}
import android.view.ViewGroup.LayoutParams._

import macroid._
import macroid.FullDsl._
import macroid.contrib.ExtraTweaks._
import macroid.util.Ui
import macroid.akkafragments.AkkaFragment

import scala.concurrent.ExecutionContext.Implicits.global

class DisplayFragment extends AkkaFragment with Contexts[AkkaFragment] {

  lazy val actorName = getArguments.getString("name")

  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  def receive = ???

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle) = getUi {

  }
}
