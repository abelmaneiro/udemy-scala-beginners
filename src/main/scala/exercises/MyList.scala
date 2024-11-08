package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyList[B]
  protected[exercises] def printElements: String
  override def toString: String = s"MyList($printElements)"
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A](myList: MyList[B]): MyList[B]
  def foreach(func: A => Unit): Unit
  def sort(func: (A, A) => Int): MyList[A]
  def zipWith[B, C](myList: MyList[B], func: (A, B) => C): MyList[C]
  def fold[B](start: B) (func: (B, A) => B): B
}

// Remember can't pass type parameters nor constructor parameters and Nothing subtype of every other class
case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException()
  override def tail: MyList[Nothing] = throw new NoSuchElementException()
  override def isEmpty: Boolean = true
  override def add[B >: Nothing] (element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""
  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  override def foreach(func: Nothing => Unit): Unit = ()
  override def sort(func: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B, C](myList: MyList[B], func: (Nothing, B) => C): MyList[C] =
    if (!myList.isEmpty) throw new Exception("Zip")
    else Empty
  override def fold[B](start: B)(func: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  // Because t is not this instance, but of another need to protected[packageName]
  override def printElements: String = {
    if (t.isEmpty) h.toString
    else s"$h,${t.printElements}"
  }
  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  override def ++[B >:A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  /*
    [1,2,3].filter(n % 2 == 0) =
      [2,3].filter(n % 2 == 0) =
      = new Cons(2, [3].filter(n % 2 == 0))
      = new Cons(2, Empty.filter(n % 2 == 0))
      = new Cons(2, Empty)
   */
  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty))))
   */
  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))
  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def foreach(func: A => Unit): Unit = {
    func(h)
    t.foreach(func)
  }
  override def sort(func: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList:MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x,Empty)
      else if (func(x, sortedList.head) <= 0) new Cons(x, sortedList)  // is less than
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(func)
    insert(h, sortedTail)
  }
  override def zipWith[B, C](myList: MyList[B], func: (A, B) => C): MyList[C] =
    if (myList.isEmpty) throw new Exception("ZipWith")
    else new Cons(func(h,myList.head), t.zipWith(myList.tail, func))
  override def fold[B](start: B)(func: (B, A) => B): B =
    t.fold(func(start, h)) (func)
}

//trait MyPredicate[-T] {
//  def test(value: T): Boolean
//}
//class EvenPredicate extends MyPredicate[Int]{
//  override def test(value: Int): Boolean = value % 2 == 0
//}

//trait MyTransformer[-A, B] {
//  def transform(value: A): B
//}
class StringToIntTransformer extends Function1[String, Int] {
  override def apply(value: String): Int =  value.toInt
}

object ListTest extends App {
  val list1 = new Cons(1, new Cons (2, new Cons(3, Empty)))
  println(list1.tail.head)  // 2
  println(list1.toString)                       // MyList(1,2,3)
  println(Empty.add(3).head)  // 3
  println(Empty.add(3).add(2).add(1).toString)  // MyList(1,2,3)
  println(Empty.toString)  // MyList()
  println(new Cons('a', new Cons('b',new Cons('c', Empty))))

  // map   6 different ways of doing the same
  val alphaNumber = new Cons("10", new Cons("20", new Cons("30", Empty)))
  println(alphaNumber.map(new StringToIntTransformer))
  println(alphaNumber.map(new Function1[String, Int] {
    override def apply(value: String): Int = value.toInt + 1 }))
  println(alphaNumber.map(new {
    override def apply(value: String): Int = value.toInt + 2 }))
  println(alphaNumber.map((value: String) => value.toInt + 3))
  println(alphaNumber.map(value => value.toInt + 4))
  println(alphaNumber.map(_.toInt + 5))

  // filter
  val numList = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  println(numList.filter(new Function1[Int, Boolean] {
    override def apply(value: Int): Boolean = value % 2 == 0
  }))
  println(numList.filter(_ % 2 == 0))

  // ++
  println(list1 ++ numList)

  //flatmap
  println(numList.flatMap(new Function1[Int, MyList[Int]]  {
    override def apply(value: Int): MyList[Int] = new Cons(value, new Cons(value + 1, Empty))
  }))
  println(numList.flatMap((value: Int) => new Cons(value, new Cons(value + 1, Empty))))

  println("Functions")
  println(numList.filter(n => n % 2 == 0))
  println(numList.map(_.toString + "A"))
  println(numList.map(n => alphaNumber.map(a => s"${n.toString}-$a")))
  println(numList.flatMap(n => alphaNumber.map(a => s"${n.toString}-$a")))

  println("FOFs and Curries Exercises")
  numList.foreach(print) ; println()
  numList.foreach(element => println(s"element: $element"))
  println(numList.sort((x, y) => y - x))
  val numList2 = new Cons(10, new Cons(20, new Cons(30, new Cons(40, Empty))))
  println(numList.zipWith(numList2, _ + _))
  println(numList.fold(100)(_ + _))

  def toCurry1(f: (Int, Int) => Int): Int => Int => Int =
    x => y => f(x, y)

  def toCurry2(f: (x: Int, y: Int) => Int): Int => Int => Int =
    x => y => f(x, y)

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int =
    (x, y) => f(x)(y)

  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))  // do g next f - compose f using g

  def componse2[A, B, C](f: A => B, g: C => A): C => B =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x =>  g(f(x))  // do f next g - f then g

  def andThen2[A, B, C](f: A => B, g: B => C): A => C =
    x =>  g(f(x))

  println(toCurry1((x, y) => x + y)(20)(4))
  println(fromCurry(x => y=> x + y)(30, 4))
  println(compose(x => x * 10, y => y + 3)(1))  // (1 + 3) * 10 = 40
  println(andThen(x => x * 10, y => y + 3)(1)) // (1 * 10) + 3 = 13
  println(componse2((x: String) => x + "AB", (y: Int) => y + "2")(10)) // (10 + "2") + "AB  = 102AB
  println(andThen2((x: String) => x + "AB", (y: String) => y + 2)("10")) // ("10" + "AB") + 2  = 10AB2

  println(for {
      n1 <- list1
      n2 <- numList
  } yield s"$n1:$n2")


}