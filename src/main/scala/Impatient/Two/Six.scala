package Impatient.Two

object Six extends App {
  val resault = "Hello".foldLeft(1: BigInt)((a, b) => a * b)      //> res4: BigInt = 9415087488
  println(resault)
}
