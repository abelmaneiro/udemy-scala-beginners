package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  //println(x.length) // throws  java.lang.NullPointerException

  // 1. Throw Exceptions
  // throw new NullPointerException("My message") // java.lang.NullPointerException: My message
  //val aWeirdValue = throw new NullPointerException(). // Exceptions are of type Nothing

  // 2. Catch exception
  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No int for you")
    else 42
  val potentialFailure: Int = try {
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a RuntimeException: " + e.getMessage)
      -1
  } finally {
    println("Finally")
  }
  println(potentialFailure)

  // 3 Define own exception
  class MyException extends Exception
  val myException = new MyException
  // throw myException  // Exception only happens when it is thrown, not when created

  // Array.ofDim[String](Int.MaxValue) // Causes java.lang.OutOfMemoryError
  def forever: Int = 1 + forever
  // forever // Causes java.lang.StackOverflowError


  object Pocket_Calculator {
    class OverflowException(message: String = "") extends ArithmeticException(message)
    class UnderflowException(message: String = "") extends ArithmeticException(message)
    class MathCalculationException(message: String = "") extends ArithmeticException(message)

    def add(x: Int, y: Int): Int = {
      val sum = x + y
      if (x > 0 && y > 0 && sum < 0) throw new OverflowException(s"$x + $y is greater than ${Int.MaxValue}")
      if (x < 0 && y < 0 && sum > 0) throw new UnderflowException(s"$x + $y is less than ${Int.MinValue}")
      sum
    }
    def subtract(x: Int, y: Int): Int = {
      val sum = x - y
      if (x > 0 && y < 0 && sum < 0) throw new OverflowException(s"$x - $y is greater than ${Int.MaxValue}")
      if (x < 0 && y > 0 && sum > 0) throw new UnderflowException(s"$x - $y is less than ${Int.MinValue}")
      sum
    }
    def multiply(x: Int, y: Int): Int = {
      val sum = x * y
      if (x > 0 && y > 0 && sum < 0) throw new OverflowException(s"$x * $y is greater than ${Int.MaxValue}")
      if (x < 0 && y < 0 && sum < 0) throw new OverflowException(s"$x * $y is greater than ${Int.MaxValue}")
      if (x < 0 && y > 0 && sum > 0) throw new UnderflowException(s"$x * $y is less than ${Int.MinValue}")
      if (x > 0 && y < 0 && sum > 0) throw new UnderflowException(s"$x * $y is less than ${Int.MinValue}")
      sum
    }
    def division(x: Int, y: Int): Int = {
      if (y == 0) throw new ArithmeticException(s"Can not divide by 0. $x / $y" )
      x / y
    }
  }

  println(Pocket_Calculator.add(0, Int.MaxValue))
  println(Pocket_Calculator.add(Int.MinValue, Int.MinValue))
  println(Pocket_Calculator.division(Int.MaxValue, Int.MaxValue))
}
