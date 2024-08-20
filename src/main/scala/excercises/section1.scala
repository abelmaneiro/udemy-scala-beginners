package excercises

object section1 extends App {
  def greetings(name: String, isFrench: Boolean): String = {
    val salutation = if (isFrench) "Bonjour" else "Hello"
    val nameLength = name.length
    val parity = if (nameLength % 2 == 0) "even" else "odd"
    val sentence = s"$salutation, $name. Your name has $nameLength characters in. This is an $parity number."
    val cost = sentence.replace(" ", "").length * 0.03
    f"$sentence This message cost £$cost%.2f"
  }

  println(greetings("Louis", true))
  println(greetings("Paul", false))
}