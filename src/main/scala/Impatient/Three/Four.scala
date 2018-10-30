package Impatient.Three

import scala.collection.mutable.ArrayBuffer

object Four extends App {
  val a = Array[Int](0,1,-5,2,3,-4,4,5,-3,-2,-1)
  println(a.mkString(","))
  val resaultOne = for(i <- a if(i > 0)) yield i
  val resaultTwo = for(i <- a if(i <= 0)) yield i
  println(resaultOne.mkString(","))
  println(resaultTwo.mkString(","))
  val resault = ArrayBuffer[Int]()
  resault ++= resaultOne
  resault ++= resaultTwo
  println(resault.mkString(","))

}
