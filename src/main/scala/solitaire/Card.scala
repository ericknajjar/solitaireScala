package solitaire

case class Card(val value:CardValue.Value, val suite:CardSuite.Value)
{

}


object Card
{
  lazy val AllCards = {

    CardSuite.values.flatMap( suite=>{

      CardValue.values.map( value=>{
        Card(value,suite)
      })
    }).toSeq

  }
}