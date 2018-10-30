package Impatient.Three

import scala.collection.mutable.ArrayBuffer

object Two extends App {
  val a = Array[Int](1,2,3,4,5)
  println(a.mkString(","))                                         //> res1: Array[Int] = Array(1, 2, 3, 4, 5)
  for(i <- 0 until (if(a.length % 2 == 0) a.length else a.length - 1) if(i % 2 == 0) ) {
    val temp = a(i)
    a(i) = a(i+1)
    a(i+1) = temp
  }
  println(a.mkString(","))
}
