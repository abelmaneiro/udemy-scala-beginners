package lectures.part1basics

object Functions extends App {
  def aFunctions(a: String, b: Int): String = {
    a + " " + b
  }
  println(aFunctions("Hello", 3))

  // Don't need to include return type for a function, but do if it's recursive
  def aParameterLessFunction1() = 42
  println(aParameterLessFunction1())
  //println(aParameterLessFunction1) // Scala 2 allows this but 3 doesn't

  def aParameterLessFunction2: Int = 42
  //println(aParameterLessFunction2()) // Scala 2 allows this but 3 doesn't
  println(aParameterLessFunction2)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n < 1)
      ""
    else
      aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("Hello", 0))
  println(aRepeatedFunction("Hello", 1))
  println(aRepeatedFunction("Hello", 2))
  println(aRepeatedFunction("Hello", 3))

  def greetings(name: String, age: Int): String = s"Hi, my name is $name and I am $age years old"
  println(greetings("Joe", 10))

  // Factorial 1 * 2 * 3 * ... * n eg. 3 * 2 * 1
  def factorial(num: Int): Int = {
    if (num <= 1)
      1
    else
      num * factorial(num - 1)
  }
  println(factorial(3))

  // Fibonacci f(n) = f(n - 1) + f(n - 2)  0, 1, 1, 2, 3, 5, 8, 13, 21, 34
  def fibonacci(num: Int): Int = {
    if (num <= 2)
      1
    else
      fibonacci(num - 1) + fibonacci(num - 2)
  }
  println(fibonacci(8))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }
  println(isPrime(7))
  println(isPrime(8))

}
