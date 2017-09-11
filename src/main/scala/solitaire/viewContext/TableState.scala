package solitaire.viewContext

import com.definitelyscala.phaser.{PhysicsObj, State}
import com.definitelyscala.phaserpixi.Point
import solitaire.cardsContext.Deck

import scala.scalajs.js.annotation.ScalaJSDefined
import solitaire.viewContext.CardsViewExtensions._

import scala.util.Random

/**
  * Created by erick on 08/09/17.
  */

@ScalaJSDefined
class TableState() extends State{


  override def preload(): Unit = {
    //game

    game.load.spritesheet("cards", "assets/cards.gif", 81, 118, 58)
    game.load.image("background","assets/feult.png")
  }


  override def create(): Unit = {

   // game.stage.backgroundColor = "rgb(68, 136, 170)";
    game.physics.startSystem(PhysicsObj.ARCADE)

    val background = game.add.tileSprite(0, 0, 256, 256, "background")

    background.width = game.width
    background.height = game.height

    val spritesheet = game.cache.getFrameByIndex("cards",0)

    val x = dealPyramid(new Point(spritesheet.width*0.75f,spritesheet.width*0.75f))
    var deck = Deck(Random.shuffle(_))

    x.foreach( p =>{

      val card = deck.Top
      println(card)
      deck = deck.Draw

      val spriteCard = new SpriteObservableAdapter(game.add.sprite(p.x,p.y,"cards",card.spriteIndex))

      spriteCard.sprite.inputEnabled = true
      spriteCard.sprite.input.enableDrag()
      spriteCard.sprite.scale.x = 0.75f
      spriteCard.sprite.scale.y = 0.75f

    })

    //card.onInputDown.map(println(_)).subscribe()


  }

  def dealPyramid(cardDimmensions:Point) = {

    val maxLineSize = 7

    val basePos = new Point(cardDimmensions.x*2,game.height - cardDimmensions.y*3 )
    val xGap = cardDimmensions.x/5.0f

    val ret = for(i <- 0 to 6;j <- 0 to (maxLineSize-1-i) )  yield  new Point({

      cardDimmensions.x*1.25*i + basePos.x + (cardDimmensions.x/2)*j

    },{

      basePos.y - cardDimmensions.x*0.5*j
    })
   

    ret.sortWith((a,b)=> a.y < b.y)

  }

  override def update(): Unit = {


  }



}
