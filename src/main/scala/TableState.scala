import com.definitelyscala.phaser.State

import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Created by erick on 08/09/17.
  */

@ScalaJSDefined
class TableState extends State{


  override def preload(): Unit = {
    //game
    game.load.spritesheet("mummy", "assets/cards.gif", 37, 45, 18)
    game.load.image("background","assets/feult.png")
  }


  override def create(): Unit = {

   // game.stage.backgroundColor = "rgb(68, 136, 170)";

    val background = game.add.tileSprite(0, 0, 256, 256, "background")
    background.width = game.width
    background.height = game.height

   // background.tileScale.x = 2.0
    //background.tileScale.y = 2.0

  }

  override def update(): Unit = {

  }



}
