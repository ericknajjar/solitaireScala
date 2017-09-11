package solitaire.viewContext

import solitaire.cardsContext.{Card, CardSuite, CardValue}


object CardsViewExtensions {

  private val cardToIndexMap = {

    val mappings = Card.AllCards.map((card)=>{

      val suite = CardSuite.values.toSeq.indexOf(card.suite)*12
      val value = CardValue.values.toSeq.indexOf(card.value)

      (card,suite+value)


    })

   mappings.toMap
  }

  class ViewCard(val card:Card) {
    val spriteIndex = {
      cardToIndexMap(card)
    }
  }

  implicit def CardToViewCard(card:Card) = new ViewCard(card)

}