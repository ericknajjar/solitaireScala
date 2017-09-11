package solitaire

class Deck private (allCards:Seq[Card]) extends IDeck
{
  override def Draw(): IDeck = {

    new Deck(allCards.drop(1))
  }

  override val IsEmpty =  allCards.isEmpty

  override def Top: Card = allCards.head
}

object Deck{

  def apply(shuffler:(Seq[Card])=>Seq[Card],allCards:Seq[Card] = Card.AllCards): IDeck = new Deck(shuffler(allCards))

}
