package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println(s"Called by value $x")
    println(s"Called by value $x")
  }

  def calledByName(x: => Long): Unit = {
    println(s"Called by name $x")
    println(s"Called by name $x")
  }

  def calledByFunc(x: () => Long): Unit = {
    println(s"Called by func " + x())
    println(s"Called by func " + x())
    println(s"Called by func address " + x)

  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
  calledByFunc(() => System.nanoTime())
}
