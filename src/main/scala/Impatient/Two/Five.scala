package Impatient.Two

object Five extends App {
  var prod: BigInt = 1                              //> prod: BigInt = 1
  for(c <- "Hello") prod *= c
  println(prod)                                      //> res3: Int = 9415087488
}
