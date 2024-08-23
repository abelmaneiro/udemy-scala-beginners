package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, age: Int = 0){
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"$name is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive:  Boolean = true
    def apply(): String = s"Hi, my name is $name, I am $age years old and I like $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
    def learns(item: String): String = s"$name is learning $item"
    def learnsScala: String = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation (operator notation) i.e. object method parameter (1 parameter)

  // operators in scala are actually methods
  val tom = new Person("Tom", "Flight Club")

  // infix notation (operator notation) i.e. object method parameter (1 parameter)
  println(mary.+(tom))
  println(mary + tom)
  println(1 + 2)
  println(1.+(2))

  // prefix notation (unary operators). can only be .unary_- unary_+ unary_~ unary_!
  println(-1)
  println(1.unary_-)
  println(!mary)
  println(mary.unary_!)

  // postfix notation (not used as when used with infix makes it harder to read the code)
  println(mary.isAlive)
  println(mary isAlive)

  // apply() - Must include ()
  println(mary.apply())
  println(mary())
  println(mary)  // print memory location of instantiated object

  // Exercises
  println((mary + "The Rockstar")())
  println(mary.+("The Rockstar")())
  println(mary.+("The Rockstar").apply())
  println((+mary)())
  println(mary.unary_+())
  println(mary.unary_+.apply())
  println(mary learnsScala)
  println(mary.learnsScala)
  println(mary(2))
  println(mary.apply(2))
}
