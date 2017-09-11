package solitaire

trait IDeck
{
  def Draw : IDeck
  def IsEmpty : Boolean
  def Top : Card
}