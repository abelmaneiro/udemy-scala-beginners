package lectures.part2oop

import scala.annotation.tailrec

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  println(person.n)
  person.greetings("Daniel")
  person.greetings()

  new Person("Peter").greetings()

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)

  val novel = new Novel("Great Expectations", 1861, author)
  println("****")
  println(novel.authorAgeAtRelease)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  println(new Counter(5).inc(-1).value)
  println(new Counter(5).inc(0).value)
  println(new Counter(5).inc(1).value)
  println(new Counter(5).inc(2).value)
  println(new Counter(5).inc(3).value)

  val counter = new Counter
  counter.print
  counter.dec.dec.dec.print
  counter.dec(3).print
}

// constructor, note need to add val or var to class parameters to make them class fields
class Person(name: String, val age:Int) {
  println("Start Person")  // all code, be it expression (end side effects) are executed when the class is instantiated
  val n = 12 / 2

  // overloading
  def greetings(name: String): Unit = println(s"${this.name} says: Hi, $name")
  def greetings(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = {
    this(name, 0)  // must be called first
    println("Aux constructor")
  }
  println("End Person")
}

class Writer(firstName: String, surname: String, val yearOfBirth: Int) {
  def fullName = s"$firstName $surname"
}
class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAgeAtRelease = {println("Age"); yearOfRelease - author.yearOfBirth}
  def isWrittenBy(author: Writer) = author == this.author
  def copy(yearOfRelease: Int) = new Novel(name, yearOfRelease, author = author)
}

class Counter(val value: Int = 0) {
  def increment(amount: Int): Counter = new Counter(value + amount)
  def increment: Counter = increment(1)
  def decrement(amount: Int): Counter = new Counter(value + amount)
  def decrement: Counter = increment(1)

  def inc: Counter = new Counter(value + 1)
  def dec: Counter = new Counter(value - 1)
  def inc(amount: Int): Counter = {
    if (amount <= 0) this
    else inc.inc(amount - 1)
  }
  def dec(amount: Int): Counter = {
    if (amount <= 0) this
    else dec.dec(amount - 1)
  }

  def print = println(value)

}
