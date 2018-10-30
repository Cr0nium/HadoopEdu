package Impatient.Three

object Three extends App {
  val a = Array[Int](1,2,3,4,5)
  println(a.mkString(","))
  val resault = for(i <- 0 until a.length) yield if(i % 2 == 1) a(i - 1) else {if(i == a.length - 1) a(i) else a( i + 1)}
  println(resault.mkString(","))
}
