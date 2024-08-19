package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    println(s"Computing factorial of $n, but first need to factorial of ${n - 1}")
    val result = if (n <= 1) 1
    else n * factorial(n - 1)
    println(s"Computed factorial of $n which is $result")
    result
  }
  println(factorial(10))

  def factorialTail(n: Int): BigInt = {
    @tailrec
    def factorialHelper(n: Int, acc: BigInt): BigInt = {
      if (n <= 1) acc
      else factorialHelper(n - 1, n * acc)
    }
    factorialHelper(n, 1)
  }
  println(factorialTail(5000))

  def concatString(str: String, num: Int): String = {
    @tailrec
    def helper(num: Int, acc: String): String = {
      if (num < 1) acc
      else helper(num - 1, str + acc)
    }
    helper(num, "")
  }
  println("0 " + concatString("Hello", 0))
  println("1 " + concatString("Hello", 1))
  println("2 " + concatString("Hello", 2))
  println("5 " + concatString("Hello", 5))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeUntil(t - 1, n % t != 0 && isStillPrime)

    isPrimeUntil(n / 2, true)
  }
  println(isPrime(17))
  println(isPrime(61))
  println(isPrime(97))
  println(isPrime(6));
  println(isPrime(62));
  println(isPrime(99));


   def fibonacci(n: Int): Int = {
     @tailrec
     def helper(i: Int, last: Int, nextToLast: Int): Int = {
       if (i >= n) last
       else helper(i + 1, last + nextToLast, last)
     }
     if (n <= 2) 1
     else helper(2, 1, 1)

   }
  println(fibonacci(8))


}
