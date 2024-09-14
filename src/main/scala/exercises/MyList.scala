package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyList[B]
  protected[exercises] def printElements: String
  override def toString: String = s"MyList($printElements)"
}

// Remember can't pass type parameters nor constructor parameters and Nothing subtype of every other class
object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException()
  override def tail: MyList[Nothing] = throw new NoSuchElementException()
  override def isEmpty: Boolean = true
  override def add[B >: Nothing] (element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""
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
}

object ListTest extends App {
  val list1 = new Cons(1, new Cons (2, new Cons(3, Empty)))
  println(list1.tail.head)  // 2
  println(list1.toString)                       // MyList(1,2,3)
  println(Empty.add(3).head)  // 3
  println(Empty.add(3).add(2).add(1).toString)  // MyList(1,2,3)
  println(Empty.toString)  // MyList()
  println(new Cons('a', new Cons('b',new Cons('c', Empty))))

}