package solitaire.tests

import solitaire._
import org.scalatest._
import solitaire.cardsContext.{Card, Deck}
import solitaire.viewContext.CardsViewExtensions._



class CardViewTests extends FlatSpec with Matchers
{

  "Card views" should "have a increasing spriteIndex" in {

    val allCards = Card.AllCards

    val indexCompared = allCards.zipWithIndex.map(t =>{

      t._1.spriteIndex == t._2
    })

    indexCompared.count((b)=>b) should be (indexCompared.length)
  }

}