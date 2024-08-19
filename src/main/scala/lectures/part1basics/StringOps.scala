package lectures.part1basics

object StringOps extends App {
  val str: String = "Hello I am learning Scala"
  //                 01234567890123456789012345
  println(str.charAt(4))  // o
  println(str.substring(12, 16)) //earn
  println(str.split(" ").toList)  // List(Hello, I, am, learning, Scala)
  println(str.startsWith("Hello"))  // true
  println(str.replace(" ", "-")) // Hello-I-am-learning-Scala
  println(str.toUpperCase)  // HELLO I AM LEARNING SCALA
  println(str.length)  // 25

  val aNumberString = "2"
  println(aNumberString.toInt + 3)  // 5
  println('a' +: aNumberString :+ 'z')  // a2z

  val name = "David"
  val age = 7
  val speed = 5.2f
  println(s"My name is $name, next birthday I will be ${age + 1} years old and I can run $speed in an hour")
      // My name is David, next birthday I will be 8 years old and I can run 5.2 in an hour
  println(f"My name is $name%s, next birthday I will be ${age + 1}%02d years old and I can run $speed%05.2f in an hour")
      //My name is David, next birthday I will be 08 years old and I can run 05.20 in an hour

  println(raw"this is a \n newline")
      // this is a \n newline
  val escaped = "this is a \n newline"
  println(raw"$escaped")
      // this is a 
      //  newline
}
