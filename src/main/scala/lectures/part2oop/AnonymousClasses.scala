package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def sleep(): Unit
    def eat(): Unit
  }

  val funnyAnimal1: Animal = new Animal {
    override def eat(): Unit = println("ha ha ha ha")
    override def sleep(): Unit = println("zz zz zz")
  }
  println(funnyAnimal1.getClass)  // class lectures.part2oop.AnonymousClasses$$anon$1

  /*  equivalent to, which compiler does behind the scenes
    class $anon$1 extends Animal {
      override def eat(): Unit = println("haa haa haa haa")
      override def sleep(): Unit = println("zzz zzz zzz")
    }
    val funnyAnimal2 = new $anon$1
    println(funnyAnimal2.getClass)  // class lectures.part2oop.AnonymousClasses$$anon$1
  */
}
