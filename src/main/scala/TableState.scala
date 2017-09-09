import com.definitelyscala.phaser.{PhysicsObj, State}
import monix.reactive.{Observable, OverflowStrategy}
import monix.execution.Scheduler.Implicits.global

import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Created by erick on 08/09/17.
  */

@ScalaJSDefined
class TableState() extends State{

  var card:SpriteObservableAdapter = null


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

    card = new SpriteObservableAdapter(game.add.sprite(25,25,"cards",0))

    card.sprite.inputEnabled = true
    card.sprite.input.enableDrag()

    card.onInputDown.map(println(_)).subscribe()


  }

  override def update(): Unit = {


  }



}
