package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1,2,3)
  println(list.head)  // 1
  println(list.tail)  // List(2,3)
  println(list.map(_ + 1)) // List(2,3,4)
  println(list.filter(x => x % 2 == 0 ))  // List(2)

  val toPair = (x: Int) => List(x, x + 10)
  println(list.flatMap(toPair))               // List(1, 11, 2, 12, 3, 13)
  println(list.flatMap(x => List(x, x + 10))) // List(1, 11, 2, 12, 3, 13)
  println(list.map(x => List(x, x + 10)))     // List(List(1, 11), List(2, 12), List(3, 13))

  val list2 = List(10,20,30)
  println(list.map { x =>
    List(x, x + 10).map { y =>
      x + y
    }
  }) // List(List(2, 12), List(4, 14), List(6, 16))
  println(list.map { x =>
  List(x, x + 10).map { y =>
    x + y
  }
  }.flatten) // List(2, 12, 4, 14, 6, 16)
  println(list.flatMap { x =>
    List(x, x + 10).map { y =>
      x + y
    }
  })  // List(2, 12, 4, 14, 6, 16)

  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val symbols = List("!", "*")
  val combinations = numbers.filter(_ % 2 == 0).flatMap { n => // for each even number
    chars.flatMap { c => // for each char
      symbols.map { s => //  for each symbol
        s"$n$c$s"
      }
    }
  }
  println(combinations) // List(2a!, 2a*, 2b!, 2b*, 2c!, 2c*, 2d!, 2d*, 4a!, 4a*, 4b!, 4b*, 4c!, 4c*, 4d!, 4d*)

  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    s <- symbols
  } yield  s"$n$c$s"
  println(forCombinations)

  numbers.foreach(x => println(x)) // print 1 2 3 4 each on a line
  for {
    n <- numbers
  } println(n)  // print 1 2 3 4 each on a line

  abstract class Maybe[+A] {
    def map[B](func: A => B): Maybe[B]
    def flatmap[B](func: A => Maybe[B]): Maybe[B]
    def filter(func: A => Boolean): Maybe[A]
  }

  case object None extends Maybe[Nothing] {
    override def map[B](func: Nothing => B): Maybe[B] = None
    override def flatmap[B](func: Nothing => Maybe[B]): Maybe[B] = None
    override def filter(func: Nothing => Boolean): Maybe[Nothing] = None
  }

  case class Some[A](value: A) extends Maybe[A] {
    override def map[B](func: A => B): Maybe[B] = Some(func(value))
    override def flatmap[B](func: A => Maybe[B]): Maybe[B] = {
      func(value)
    }
    override def filter(func: A => Boolean): Maybe[A] = if (func(value)) Some(value) else None
  }

  val some1 = Some(1)
  val some2 = Some(2)
  val none: Maybe[Int] = None

  println(some1.map(x => x + 10))
  println(some1.flatmap{x =>
    some2.map(y => x + y)
  })
  println(none.map(x => x + 20))
  println(none.flatmap(x => some1.map(y => x + y)))
  println(some1.flatmap(x => none.map(y => x + y)))
}
