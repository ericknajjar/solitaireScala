package solitaire.cardsContext

import scala.util.Random

case class Card(val value:CardValue.Value, val suite:CardSuite.Value)
{

}


object Card
{
  lazy val AllCards = {

    var ret = Seq[Card]()

    for( suite <- CardSuite.values;value <-CardValue.values) {

      ret = ret:+ Card(value,suite)

    }

    ret

  }
}