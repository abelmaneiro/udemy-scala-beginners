package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2  // Expression
  println(x)
  println(2 + 3 * 4)

  // maths operations + - * / & | ^ << >> >>>(right shift with zero extensions)
  // relational operations == != > >= < <=
  // boolean logical operations ! && ||
  // change value operations += -= *= /=     side effects

  // Instructions (are Executed, DO thing, Imperative language) vs
  // Expressions (are evaluated, has a value and a type, Functional language)

  // Side effect return Unit (). E.g. println(), while loop, reassigning =
  val aPrintln = println("Unit ()")
  println(aPrintln)

  // Code block is an expression like everything else in Scala.
  // The value of the code block is the value of the last expression
  val aCodeBlock = {  // type Boolean, value of true
    2 < 3
  }
}
