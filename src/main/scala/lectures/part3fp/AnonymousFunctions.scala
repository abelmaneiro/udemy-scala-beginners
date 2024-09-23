package lectures.part3fp

object AnonymousFunctions extends App {
  val doubler = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
  val doublerLong     : Int => Int = (x:Int) => x * 2
  val doublerShort    : Int => Int = x => x * 2
  val doublerShortish : Int => Int = _ * 2
  val doublerShorter  = (x: Int) => x * 2
  val doubler5 = { (x: Int) =>
    x * 2
  }
  val doubler6 =  (x: Int) => {
    x * 2
  }

  val adderLong       : (Int, Int) => Int = (x: Int, y: Int) => x + y
  val adderShort      : (Int, Int) => Int = (x, y) => x + y
  val adderShortish   : (Int, Int) => Int = _ + _
  val adderShorter    = (x: Int, y: Int) => x + y
  val adder5 = { (x: Int, y: Int) =>
    x + y
  }

  val something1   : () => Int = () => 42
  val something2   = () => 42
  val something3 = { () =>
    42
  }
  println(something1)    // lectures.part3fp.AnonymousFunctions$$$Lambda$25/0x00000008000bec40@87f383f
  println(something1())  //42 as it's something.apply()

  val superAdderLong: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val superAdd4 = (x: Int) => (y: Int) => x + y
  def superAdd5(x:Int)(y: Int) = x + y
  val s1 = superAdd4(3)
  val s2 = superAdd5(4)
  println(s1(10))  // 13
  println(s2(10))  // 14
  println(s1)
  println(s2)

}
