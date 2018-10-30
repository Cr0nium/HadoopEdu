package Impatient.Two

object Ten extends App {
  def productRec(s: String): BigInt = {
    if (s.length == 0) 1
    else s.head * productRec(s.tail)
  }                                        //> productRec: (s: String)BigInt
  println(productRec("Hello"))                       //> res6: BigInt = 9415087488
}
