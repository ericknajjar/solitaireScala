package solitaire.tests

import solitaire._
import org.scalatest._




class DeckTests extends FlatSpec with Matchers {

  val r = new scala.util.Random

  "A deck" should "have 52 cards" in {

    var deck = Deck(shuffler)

    var ammount = 0

    while (!deck.IsEmpty) {

      deck = deck.Draw
      ammount+=1
    }


    ammount should be (52)
  }

  it should "have one, and only one of each Card" in {


    var deck = Deck(shuffler,Card.AllCards)


    val cards = Iterator.iterate(deck)(_.Draw).takeWhile(!_.IsEmpty).map(_.Top).toSeq

    val remain = Card.AllCards.filterNot(cards.contains(_))

    cards.length should be (Card.AllCards.length)

    remain.length should be (0)

  }

  it should "should call the shuffler only once" in {

    var called = 0
    var deck = Deck((cards)=>{
      called+=1
      println(called)
      cards
    },Card.AllCards)

    val seq = Iterator.iterate(deck)(_.Draw).takeWhile(!_.IsEmpty).map(_.Top).toSeq

    seq.length should be (52)
    called should be (1)

  }

  it should "should call the shuffler on creations" in {

    var called = false
    var deck = Deck((cards)=>{
      called =true
      cards
    },Card.AllCards)


    called should be (true)

  }

  def shuffler(cards: Seq[Card]): Seq[Card] = {

   cards

  }

}