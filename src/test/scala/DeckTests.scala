package solitair.tests

import org.scalatest._

sealed trait IDeck
{
  def Draw : IDeck
  def IsEmpty : Boolean
}

class Deck private (rnd:(Int)=>Int,totalCards:Int) extends IDeck
{
  override def Draw(): IDeck = {

    new Deck(rnd,totalCards-1)
  }

  override val IsEmpty = totalCards <=0
}

object Deck{

  def apply(rnd: (Int) => Int): IDeck = new Deck(rnd,52)

}

class DeckTests extends FlatSpec with Matchers {

  val r = new scala.util.Random

  "A deck" should "have 52 cards" in {

    var deck = Deck(rnd)

    var ammount = 0

    while (!deck.IsEmpty) {

      deck = deck.Draw
      ammount+=1
    }

    ammount should be (52)
  }

  def rnd(until: Int): Int = {

    r.nextInt(until)

  }

}