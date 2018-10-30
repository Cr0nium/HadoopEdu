package Demo

object Karrier extends App {
  def summa(a:Int)(b:Int) = a + b
  val sumWithA10:(Int=>Int) = summa(10)
  println(sumWithA10(30))
}
