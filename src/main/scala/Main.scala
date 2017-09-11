import com.definitelyscala.phaser._
import solitaire.viewContext.TableState

/**
  * Created by erick on 23/08/17.
  */
object Main  {


  def main(args: Array[String]): Unit = {

    val game = new Game(width = 800, height = 520,renderer = Phaser.CANVAS, parent = "solitair-container",state = new TableState())

  }

}
