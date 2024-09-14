package lectures.part2oop

object Generics extends App {

  // *** Generic Types
  class MyList[A]
  val listOfIntegers: MyList[Int] = MyList[Int]
  val ListOfStrings = MyList[String]

  class MyMap[Key, value]
  val myMap = new MyMap[Int, String]

  // *** Generic Methods
  object MyList {
    def empty[A]: MyList[A] = new MyList[A]
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // *** Variants (for classes and traits only)
  class Animal
  class Cat extends Animal
  class Alligator extends Animal

  // Invariant - Must be of the same type
  class InvariantList[A]
  val invariantList: InvariantList[Cat] = new InvariantList[Cat]
  // Covariant - Allows same type and subtype but not supertype
  class CovariantList[+A]
  val covariantList: CovariantList[Animal] = new CovariantList[Cat]
  // Contravariant - Allows same type and supertypes but not subtypes
  class Contravariant[-A]
  val contravariant: Contravariant[Cat] = new Contravariant[Animal]

  // *** Upper and Lower Bounded.
  // Lower Bounded - B must be a subtype of A (i.e. Animal)
  class Cage[B <: Animal] (species: B)
  val cage = new Cage(new Cat)   // val cage: Cage[Cat]
  // Upper Bounded - B must be a supertype of A (i.e Alligator)
  class Living[B >: Alligator] (species: B)
  val living1 = new Living(new Alligator)  // val living1: Living[Alligator]
  val living2 = new Living(new Cat)        // val living2: Living[Animal]  - as Animal is common ancestor

  // solving Covariant with Upper Bounds
  class MyList2[+A] {
    def add[B >: A](element: B): MyList2[B] = new MyList2[B]
  }
  val add1 = new MyList2[Cat].add(new Cat)        // val add1: MyList2[Cat]
  val add2 = new MyList2[Cat].add(new Alligator)  // val add2: MyList2[Animal]  - as Animal is common ancestor



}
