package Impatient.Three

object One extends App {
  val n = 13
  val a = for (i <- 0 until n) yield i
  println(a)
  println(a.getClass)
}
