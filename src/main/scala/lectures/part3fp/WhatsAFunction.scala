package lectures.part3fp

object WhatsAFunction extends App {

  trait MyAction[A, B] {
    def execute(element: A): B
  }
  val myAction = new MyAction[Int, String] {
    override def execute(element: Int): String = s"Hi, ${element.toString}"
  }
  println(myAction.execute(5))

  trait MyFunction[A, B] {
    def apply(element: A):B
  }
  val doublerFunc = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doublerFunc(5))

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(s: String): Int = s.toInt
  }
  val stringToIntConverterAnon: String => Int = (s: String) => s.toInt
  println(stringToIntConverter("20") + 1)
  println(stringToIntConverterAnon("21") + 1)

  private val adder = new Function2[Int, Int, Int]{
    override def apply(x: Int, y: Int): Int = x + y
  }
  val adderAnonymous: (Int, Int) => Int = (x, y) => x + y
  println(adder(2, 3))
  println(adderAnonymous(4, 5))


  def concatString1: Function2[String, String, String] = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  val concatString2: (String, String) => String = (s1: String, s2: String) => s1 + s2
  println(concatString1("qwe", "rty"))
  println(concatString2("abc","def"))


  def supperAdder1: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  val supperAdder2: Int => Int => Int = (x: Int) => (y: Int) => x + y
  println(supperAdder1.apply(2).apply(4))
  println(supperAdder2(3)(4))
  val adder3: Int => Int = supperAdder1(3)
  println(adder3(5))

}
