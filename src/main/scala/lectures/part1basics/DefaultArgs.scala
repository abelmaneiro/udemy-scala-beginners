package lectures.part1basics

object DefaultArgs extends App {
  def savePicture(name: String, width: Int = 1920, height: Int = 1080, format: String = "jpeg"): Unit =
    println("Saving")
    
  savePicture("cats", format = "img")
}
