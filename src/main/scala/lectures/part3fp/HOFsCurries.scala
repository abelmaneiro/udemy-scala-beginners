package lectures.part3fp

import scala.annotation.tailrec

object HOFsCurries extends App {
//  val supperFunctions: (Int, (String, Int => Boolean) => Int) => Int => Int =
//    (n1: Int, func: (String, Int => Boolean) => Int) =>
//      (n2: Int) =>
//        (n1: Int) + func("A", _ == 0) + n2: Int
//
//  supperFunctions(1,("A", ))

  @tailrec
  def nTimes(func: Int => Int, count: Int, value: Int): Int = {
    if (count < 1) value
    else nTimes(func, count - 1, func(value))
  }
  println(nTimes(_ + 2, 5, 0)) // 0 + 2 + 2 + 2 + 2 + 2 = 10

  def nTimesBetter(func: Int => Int, count: Int): Int => Int = {
    if (count < 1) (value: Int) => value
    else (value: Int) => nTimesBetter(func, count - 1) (func(value))
  }

  def nTimesEvenBetter(func: Int => Int, count: Int): Int => Int = { value =>
    if (count < 1) value
    else nTimesEvenBetter(func, count -1) (func(value))
  }

  @tailrec
  def nTimesSimple(func: Int => Int, count: Int)(value: Int): Int = {
    if (count < 1) value
    else nTimesSimple(func, count -1)(func(value))
  }
  val fiveTwos = nTimesSimple(_ + 2, 5)
  println(fiveTwos(0))

}
