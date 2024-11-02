package exercises

object Maybe extends App {
  abstract class Maybe[+A] {
    def map[B](func: A => B): Maybe[B]
    def flatMap[B](func: A => Maybe[B]): Maybe[B]
    def filter(func: A => Boolean): Maybe[A]
  }

  case object None extends Maybe[Nothing] {
    override def map[B](func: Nothing => B): Maybe[B] = None
    override def flatMap[B](func: Nothing => Maybe[B]): Maybe[B] = None
    override def filter(func: Nothing => Boolean): Maybe[Nothing] = None
  }

  case class Some[+A](value: A) extends Maybe[A] {
    override def map[B](func: A => B): Maybe[B] = Some(func(value))
    override def flatMap[B](func: A => Maybe[B]): Maybe[B] = func(value)
    override def filter(func: A => Boolean): Maybe[A] = if (func(value)) this else None
  }

  val some1 = Some(1)
  val some2 = Some(2)
  val none: Maybe[Int] = None

  println(some1.map(x => x + 10))
  println(some1.flatMap{ x =>
    some2.map(y => x + y)
  })
  println(some1.filter((x => x == 2)))
  println(some1.filter((x => x == 1)))
  println(none.map(x => x + 20))
  println(none.flatMap(x => some1.map(y => x + y)))
  println(some1.flatMap(x => none.map(y => x + y)))
  println(none.filter((x => x == 2)))

}
