package exercises

object Section2 extends App {

  trait Vegetarian(val isVegetarian: Boolean) {

    override def toString: String = if (isVegetarian) "Vegetarian" else "Non-Vegetarian"
  }

  abstract class MenuItem(val name: String, val price: Int, val description: String, isVegetarian: Boolean)
    extends Vegetarian(isVegetarian) {

    override def toString: String = f"$name%-20s | £${price / 100.0}%8.2f | $description%-50s " +
      f"| ${super[Vegetarian].toString}%14s"
  }

  abstract class foodItem(name: String, price: Int, description: String, isVegetarian: Boolean)
    extends MenuItem(name, price, description, isVegetarian)

  class MainDish(name: String, price: Int, description: String, isVegetarian: Boolean)
    extends foodItem(name, price, description, isVegetarian) {

    override def toString = s"Main Dish  | ${super.toString}"
  }

  class SideDish(name: String, price: Int, description: String, isVegetarian: Boolean)
    extends foodItem(name, price, description, isVegetarian: Boolean) {

    override def toString = s"Side Dish  | ${super.toString}"
  }

  trait Alcoholic(val isAlcoholic: Boolean) {
    override def toString: String = if (isAlcoholic) "Alcoholic" else "Non-Alcoholic"
  }

  abstract class DrinkItem(name: String, price: Int, description: String, isVegetarian: Boolean, milliliters: Int,
                           isAlcoholic: Boolean)
    extends MenuItem(name, price, description, isVegetarian) with Alcoholic(isAlcoholic) {

    override def toString: String = f"${super[MenuItem].toString} | $milliliters%4d ml | ${super.toString}%13s"
  }

  class SoftDrink(name: String, price: Int, description: String, isVegetarian: Boolean, milliliters: Int)
    extends DrinkItem(name, price, description, isVegetarian: Boolean, milliliters, false) {

    override def toString = s"Soft Drink | ${super.toString}"

  }

  class Beer(name: String, price: Int, description: String, isVegetarian: Boolean, milliliters: Int, isAlcoholic: Boolean)
    extends DrinkItem(name, price, description, isVegetarian: Boolean, milliliters, isAlcoholic) {

    override def toString = s"Beer Drink | ${super.toString}"
  }

  println(new MainDish("Fish and Chips", 1651, "Catch of the day served with chips", false))
  println(new SideDish("Cheese Chips", 361, "Chips liberated coated with cheese", true))
  println(new SoftDrink("Cola", 245, "Ice cold Cola", true, 250))
  println(new Beer("Tiger", 245, "Finest Singapore beer", true, 750, true))
  println(new Beer("Heineken 0", 245, "Dutch Zero beer", true, 330, false))

}
