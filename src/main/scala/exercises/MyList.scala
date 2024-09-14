package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyList[B]
  protected[exercises] def printElements: String
  override def toString: String = s"MyList($printElements)"
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](myList: MyList[B]): MyList[B]
}

// Remember can't pass type parameters nor constructor parameters and Nothing subtype of every other class
object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException()
  override def tail: MyList[Nothing] = throw new NoSuchElementException()
  override def isEmpty: Boolean = true
  override def add[B >: Nothing] (element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""
  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
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
  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty))))
   */
  override def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))
  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(value: T): Boolean
}
class EvenPredicate extends MyPredicate[Int]{
  override def test(value: Int): Boolean = value % 2 == 0
}

trait MyTransformer[-A, B] {
  def transform(value: A): B
}
class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(value: String): Int =  value.toInt
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
  println(alphaNumber.map(new MyTransformer[String, Int] {
    override def transform(value: String): Int = value.toInt + 1 }))
  println(alphaNumber.map(new {
    override def transform(value: String): Int = value.toInt + 2 }))
  println(alphaNumber.map((value: String) => value.toInt + 3))
  println(alphaNumber.map(value => value.toInt + 4))
  println(alphaNumber.map(_.toInt + 5))

  // filter
  val numList = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  println(numList.filter(new MyPredicate[Int] {
    override def test(value: Int): Boolean = value % 2 == 0
  }))
  println(numList.filter(_ % 2 == 0))

  // ++
  println(list1 ++ numList)

  //flatmap
  println(numList.flatMap(new MyTransformer[Int, MyList[Int]]  {
    def transform(value: Int): MyList[Int] = new Cons(value, new Cons(value + 1, Empty))
  }))
  println(numList.flatMap((value: Int) => new Cons(value, new Cons(value + 1, Empty))))

}