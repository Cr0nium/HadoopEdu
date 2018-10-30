package RoadToBigData

import scala.io.StdIn

object MapperDistinctPhaseTwo_v1 {
  def main(args: Array[String]) {
    var line = ""
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      val (key, category) = (line.trim.split(",")(0), line.trim.split(",")(1))
      if (key != "") {
        println(category + "\t1" )
      }
    }
  }
}
