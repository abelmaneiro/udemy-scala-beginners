package lectures.part2oop

object Objects extends App {
  println("before Person Object")

  // Unlike Java, Scala does not have class-level functionality (i.e. Static)
  object Person {
    // static / class level functionality
    println("In Person Object")  // Object only gets created first time object is referenced
    // equivalent to Java's class-level functionality
    val N_EYES = 2
    def canFly = false

    // factory method
    def apply(name: String) = new Person(name)
  }

  class Person (val name: String) {
    // instance-level functionality
  }
  //Note Both object Person and class Person are "companions"

  println("after Person Object")
  println(Person.N_EYES)
  println(Person.canFly)

  // Scala objects are Singletons instances
  val mary = Person
  val john = Person
  println(mary == john)  // true
  // Instances of class are not
  val ann1 = new Person("Ann")
  val ann2 = new Person("Ann")
  println(ann1 == ann2) // false

  // Use factory to create the instance
  val Bob = Person("Bob")
  val Jim = Person.apply("Jim")

}
