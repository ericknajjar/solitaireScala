package solitaire.viewContext


import com.definitelyscala.phaser.Sprite
import monix.execution.Cancelable
import monix.reactive
import monix.reactive.Observable

/**
  * Created by erick on 08/09/17.
  */
class SpriteObservableAdapter(val sprite:Sprite){


  val onInputDown = Observable.create[Int](new reactive.OverflowStrategy.ClearBuffer(2)) { downstream =>

    sprite.events.onInputDown.add(()=>{
      downstream.onNext(1)
    },null,0)

    Cancelable.empty
  }





}


