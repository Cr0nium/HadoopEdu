package RoadToBigData

import scala.io.StdIn

object reduceOne {
  def main(args: Array[String]) {
    var line = ""
    val reducer = scala.collection.mutable.Map("lastKey" -> null, "sum" -> 0)
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      val subArray = line.trim.split("\t")
      val keyValue = Map("key" -> subArray(0), "value" -> subArray(1).toInt)
      if ((reducer("lastKey") != null) && (reducer("lastKey") != keyValue("key"))) {
        println(reducer("lastKey") + "\t" + reducer("sum"))
        reducer("lastKey") = keyValue("key")
        reducer("sum") = keyValue("value")
      } else {
        reducer("lastKey") = keyValue("key")
        val resault = reducer("sum").toString.toInt + keyValue("value").toString.toInt
        reducer("sum") = resault
      }
    }
    if (reducer("lastKey") != null) println(reducer("lastKey") + "\t" + reducer("sum"))
  }
}
