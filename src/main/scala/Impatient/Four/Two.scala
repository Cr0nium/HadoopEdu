package Impatient.Four

import scala.collection.mutable
import scala.io.Source

object Two {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("C:\\Users\\timur\\Desktop\\input.txt")
    val iterator = source.getLines()
    val result = new mutable.HashMap[String, Int]
    for (i <- iterator) if (result.contains(i)) result(i) += 1 else result(i) = 1
    println(result)
  }
}
