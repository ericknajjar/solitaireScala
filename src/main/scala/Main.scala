import com.definitelyscala.phaser._
/**
  * Created by erick on 23/08/17.
  */
object Main  {

  def main(args: Array[String]): Unit = {
    println("Hello world! ")
    val game = new Game(width = 800, height = 520,renderer = Phaser.CANVAS, parent = "solitair-container")
    println(game.height)
  }

}