package solitaire.viewContext

import solitaire.cardsContext.{Card, CardSuite, CardValue}


object CardsViewExtensions {

  private val cardToIndexMap = {

    val mappings = Card.AllCards.map((card)=>{

      val suite = CardSuite.values.toSeq.indexOf(card.suite)*13
      val value = CardValue.values.toSeq.indexOf(card.value)

      (card,suite+value)


    })

   mappings.toMap
  }

  implicit class ViewCard(val card:Card) extends AnyVal{
    def spriteIndex = {
      cardToIndexMap(card)
    }
  }


}