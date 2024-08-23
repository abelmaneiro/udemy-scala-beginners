package lectures.part2oop

object Inheritance extends App {

  // Super class of Cat
  class Animal {
    def eat: String = "yum yum"
  }

  // Subclass of Animal
  // Single Class inheritance
  class Cat extends Animal

  println((new Cat).eat)
  private val cat = new Cat
  println(cat.eat)

  // Constructors
  class Insect (name: String, color: String){
    def this(name: String) = this(name, "")
    val location: String = "unknown"
  }
  class Spider(name: String, color: String, venom: String) extends Insect(name, color)
  class Ant(name: String) extends Insect(name, "black")
  class Beetle(name: String) extends Insect(name)
  val spider = new Spider("widow", "black", "toxic")
  val ant = new Ant("worker")
  val beetle = new Beetle("royal")

  // override field from superclasses in the constructor
  class Bee(name: String, override val location: String) extends Insect(name)
  class Ladybug(name: String, aLocation: String) extends Insect(name){
    override val location: String = aLocation
  }
  class Earwig(name: String, location: String) extends Insect(name)
  println( new Bee("Worker", "Ireland").location )     // Ireland
  println( new Ladybug("Royal", "England").location )  // England
  println( new Earwig("Lord", "Spain").location)       // Unknown

  // super
  class Person {
    def talk: String = "Hi"
  }
  class Kid extends Person{
    override def talk: String = super.talk  + ", I am a kid"
  }
  println( new Kid().talk)

}
