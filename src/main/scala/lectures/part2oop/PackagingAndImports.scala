package lectures.part2oop



object PackagingAndImports extends App {

  // Package Members are accessible e.g. Everything off lectures.part2oop
  val writerAnn = new Writer("Ann", "You Rock", 1923)
  val writerPeter = new lectures.part2oop.Writer("Peter", "You Rock", 1923)
  val myCat1 = new Inheritance.Cat
  val myCat2 = new lectures.part2oop.Inheritance.Cat

  // Import package
  import playground.Cinderella
  val princess1 = new Cinderella
  val princess2 = new playground.Cinderella

  // Package Object
  sayHello()
  println(SPEED_OF_LIGHT)

  // Import alias
  import playground.{PrinceCharming => Prince}
  val prince = new Prince
  //useful when have classes with the same name from different packages
  import java.util.{Date => JavaDate}
  import java.sql.{Date => SqlDate}
  val javaDate1 = JavaDate()
  val sqlDate1 = SqlDate(0)
  // or just fully qualifying
  val javaDate2 = java.util.Date()
  val sqlDate2 = java.sql.Date(0)
}
