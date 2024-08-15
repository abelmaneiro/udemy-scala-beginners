package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  val y = 42
  println(x)
  // VAL ARE IMMUTABLE, don't need to provide type as compiler can infer types
  // x = 2  is illegal
  val aString: String = "hi"
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val aInt: Int = 34
  val aShort: Short = 2
  val aLong: Long = 123456789012345L
  val aFloat: Float = 2.1f
  val aDouble: Double = 3.14

  var aVaribale = 4
  aVaribale = 5
  // VAR ARE MUTABLE, have side efect

}
