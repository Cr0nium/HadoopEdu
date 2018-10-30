package RoadToBigData

import scala.collection.mutable.HashMap
import scala.io.StdIn

object In_mapper_combining_v1 {
  def main(args: Array[String]) {
    var line = ""
    val associateArray = new HashMap[String, Int]()
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      for (token <- line.trim.split(" ")) if (token != "") {
        if (associateArray.contains(token)) associateArray(token) += 1
        else associateArray(token) = 1
      }
      for ((key, value) <- associateArray) println(key + "\t" + value)
      associateArray.clear()
    }
  }
}
