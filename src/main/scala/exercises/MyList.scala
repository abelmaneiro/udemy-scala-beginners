package exercises

abstract class MyList {
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  protected[exercises] def printElements: String
  override def toString: String = s"MyList($printElements)"
}

object Empty extends MyList {
  override def head: Int = throw new NoSuchElementException()
  override def tail: MyList = throw new NoSuchElementException()
  override def isEmpty: Boolean = true
  override def add(element: Int): MyList = new Cons(element, Empty)
  override def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h
  override def tail: MyList = t
  override def isEmpty: Boolean = false
  override def add(element: Int): MyList = new Cons(element, this)
  // Because t is not this instance, but of another need to protected[packageName]
  override def printElements: String = {
    if (t.isEmpty) h.toString
    else s"$h,${t.printElements}"
  }
}

object ListTest extends App {
  val list1 = new Cons(1, new Cons (2, new Cons(3, Empty)))
  println(list1.tail.head)  // 2
  println(list1.toString)   // MyList(1,2,3)
  println(Empty.add(3).head)  // 3
  println(Empty.add(3).add(2).add(1).toString)  // MyList(1,2,3)
  println(Empty.toString)  // MyList()
}