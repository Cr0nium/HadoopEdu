package RoadToBigData

import scala.io.StdIn

object MapperDistinctPhaseOne_v1 {
  def main(args: Array[String]) {
    var line = ""
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      val subArray = line.trim.split("\t")
      val (key, value) = (line.trim.split("\t")(0), line.trim.split("\t")(1).split(","))
      for (token <- value) if (key != null) println(key + "," + token + "\t" + "1")
    }
  }
}
