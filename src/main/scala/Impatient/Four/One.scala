package Impatient.Four

object One {
  def main(args: Array[String]) {
    val gifts = Map("Наушники" -> 8000, "Компьютер" -> 150000, "Машина" -> 1200000)
    println(gifts)
    val result = for ((key, value) <- gifts) yield (key, (value*0.9).toInt)
    println(result)
  }
}
