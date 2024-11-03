package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  val aSeq = Seq(1,2,3,4)
  println(aSeq)  // List(1, 2, 3, 4)
  println(aSeq(2))  // 3
  println(aSeq :+ 5) // List(1, 2, 3, 4, 5)
  println(0 +: aSeq) // List(0, 1, 2, 3, 4)
  println(aSeq ++ Seq(5,6,7,8)) // List(1, 2, 3, 4, 5, 8, 7, 8)

  val aRange = 1 to 10
  val bRange = 1.until(10)
  val cRange = Range(1, 10)
  println(aRange.sum)  // 55
  println(bRange.sum)  // 45
  println(cRange.sum)  // 45
  println(aRange.mkString(",")) // 1,2,3,4,5,6,7,8,9,10
  println(bRange.mkString(",")) // 1,2,3,4,5,6,7,8,9
  println(cRange.mkString(",")) // 1,2,3,4,5,6,7,8,9
  println((1 to 10).map(_ + 10)) //Vector(11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

  val aList = List(1,2,3)
  println(0 :: aList) // List(0, 1, 2, 3) alternative to 0 +: aList
  println(List.fill(3)(Random.nextInt(100)))  // List(98, 91, 38)
  println(List.fill(3)("A-"))                 // List(A-, A-, A-)
  println(List.tabulate(3)(x => s"A$x"))      // List(A0, A1, A2)


  println(new Array[Int](3).mkString(",")) // 0,0,0
  println(new Array[String](3).mkString(",")) // null,null,null
  println(Array.ofDim[Int](2).mkString(","))  // 0,0
  println(Array.ofDim[String](2).mkString(",")) // null,null
  val numbers = Array(1,2,3)
  numbers(0) = 9
  println(numbers.mkString(","))

  val random = new Random
  val maxRuns = 10000
  val maxElements = 10000
  def getWriteTime(seq: Seq[Int]): Double = {
    val runs = for {
      _ <- 1 to maxRuns
    } yield {
      val random = Sequences.random.nextInt(maxElements)
      val savedTime = System.nanoTime()
      seq.updated(random, random)
      System.nanoTime() - savedTime
    }
    runs.sum.toDouble / maxRuns
  }
  println(s"List   ${getWriteTime((1 to maxElements).toList)}")
  println(s"Vector ${getWriteTime(Vector.fill[Int](maxElements)(1))}")




}
