package exercises

object Section3 extends  App {
  class Box[A](val value: A) {
  }
  println(Box(123).value)
  println(Box("I'm in a box").value)

  trait Vegetarian(val isVegetarian: Boolean) {
    override def toString: String = if (isVegetarian) "Vegetarian" else "Non-Vegetarian"
  }

  class MenuItem[A](val name: String, val price: Int, val description: String, isVegetarian: Boolean, item: A)
    extends Vegetarian(isVegetarian) {
    override def toString: String = f"$name%-20s | £${price / 100.0}%8.2f | $description%-50s " +
      f"| ${super[Vegetarian].toString}%14s " + item
  }
  val a = new MenuItem("cake", 223, "nice cake", true, "This thing?")
  println(a)

  class MenuItem2(val name: String, val price: Int, val description: String, isVegetarian: Boolean, item: MenuItemType)
    extends Vegetarian(isVegetarian) {
    override def toString: String = {
      if (price < 0) throw new InvalidMenuItemException
      f"$name%-20s | £${price / 100.0}%8.2f | $description%-50s " +
      f"| ${super[Vegetarian].toString}%14s " + item
    }
    def safeDescription:String = {
      try {
        toString
      } catch {
        case e: InvalidMenuItemException=> "There was a Invalidmenuitems"
      }
    }
  }

  sealed trait MenuItemType
    case object MainDish extends MenuItemType
    case object SideDish extends MenuItemType
    case object Drink extends MenuItemType

  case class ItemPrice(value: Int) {
    def toPounds: String = f"£${value / 100.0}%.2f"
  }

  val b = new MenuItem2("cake", -1, "nice cake", true, MainDish )
  println(b.safeDescription)
//  print(b)

  class InvalidMenuItemException extends Exception

  println(ItemPrice(231).toPounds)

}

