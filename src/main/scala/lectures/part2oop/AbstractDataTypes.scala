package lectures.part2oop

object AbstractDataTypes extends App {

  abstract class Creature {
    val creatureType: String // Abstract
    def eat(): Unit = println("eat")
  }

  abstract class Animal extends Creature {
    override def eat(): Unit = println("animal")
  }

  class Dog extends Animal  {
    override val creatureType: String = "Canine"
    override def eat(): Unit = println("crunch crunch")
  }

  trait Carnival {
    def eat(animal: Animal): Unit  // Abstract
    def eat(): Unit = println("carnival")
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnival with ColdBlooded {
    override val creatureType: String = "Crocodile"
    override def eat(): Unit = {
//      super[Creature].eat() // this is grand pa
      super[Animal].eat()
      super[Carnival].eat()
      println("snap snap")
    }
    override def eat(animal: Animal): Unit = println(s"I am a Crocodile eating ${animal.creatureType}")
  }


  val crocodile = new Crocodile
  crocodile.eat()
  crocodile.eat(new Dog)


}
