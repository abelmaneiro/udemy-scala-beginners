package lectures.part2oop

object CaseClasses extends App {

   case class Person(name: String, age: Int)

  // 1 class parameters are promoted to val fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2 Sensible toString
  println(jim.toString)

  // 3 equals and hashcode implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim2.equals(jim)) // True
  println(jim2 == jim)      // True
  println(jim2.eq(jim))     // False as eq always checks pointer
  println(jim2.eq(jim2))    // True as pints to same instance

  // 4 have copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5 Have companion objects with default apply factory constructor
  val marry = Person("Mary", 32)
  val marry2 = Person.apply("Mary", 32)
  
  // 6 Are serializable
  
  // Have extractor patters so can be used in PATTERN matching
  
}
