package exercises

object Section4 extends App {
  // Task 1
  println(Seq(1, 2, 3, 5, 7, 9, 12, 15).map(_ * 5))
  println(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(_ % 2 == 0))

  // Task 2
  def ifMultiOfFive(value: Option[Int]): Option[Boolean] = value.map(_ % 5 == 0)

  println(ifMultiOfFive(Option(5)))
  println(ifMultiOfFive(Some(15)))
  println(ifMultiOfFive(Some(3)))
  println(ifMultiOfFive(None))

  // Task 3
  def addSeqMap(seq1: Seq[Int], seq2: Seq[Int]): Seq[Int] =
    seq2.flatMap { s2 =>
      seq1.map(s1 => s1 + s2)
    }

  println(addSeqMap(Seq(1, 2), Seq(4, 9)))

  // Task 4
  def addSeqFor(seq1: Seq[Int], seq2: Seq[Int]): Seq[Int] =
    for {
      s2 <- seq2
      s1 <- seq1
    } yield s1 + s2

  println(addSeqFor(Seq(1, 2), Seq(4, 9)))

  // Task 5.1
  sealed trait ProductStyle
  case object Cone extends ProductStyle
  case object Tub extends ProductStyle
  case class Product(description: String, style: ProductStyle, price: Double)
  val products = List(
    Product("Chocolate Cone", Cone, 2.00),
    Product("Chocolate Tub", Tub, 4.50),
    Product("Mint Chocolate Cone", Cone, 2.20),
    Product("Mint Chocolate Tub", Tub, 4.50),
    Product("Strawberry Cone", Cone, 2.20),
    Product("Strawberry Tub", Tub, 4.50),
  )
  // task 5.2
  def productsTotalPrice(products: List[Product]): Double = products.map(_.price).sum
  println(productsTotalPrice(products))
  // task 5.3
  def productsTotalPriceWithDiscount(products: List[Product], discount: Double, style: ProductStyle): Double =
    products.map { p =>
      if (p.style == style)
        p.price - (p.price * (discount / 100))
      else
        p.price
    }.sum
  println(productsTotalPriceWithDiscount(products, 10, Cone))
  // task 5.4
  def printProducts(products: List[Product]) = if (products.isEmpty) "" else products.map {p =>
    s"${p.description}  @ ${p.price}"
  }.reduce((a, b) => a + "\n" + b)
  println(printProducts(List()))
  println(printProducts(products))
  print("end")

  


}
