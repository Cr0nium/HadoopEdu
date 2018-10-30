package RoadToBigData

import scala.io.StdIn

object ReducerDistinct_v1 {
  def main(args: Array[String]) {
    var line = ""
    var (lastKey, lastCategory) = ("", "")
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      val (key, category) = (line.trim.split("\t")(0).split(",")(0), line.trim.split("\t")(0).split(",")(1))
      if ((lastKey != "") && ((lastCategory != category) || (lastKey != key))) {
        println(lastKey + "," + lastCategory)
        lastKey = key
        lastCategory = category
      } else {
        lastKey = key
        lastCategory = category
      }
    }
    if (lastKey != "") println(lastKey + "," + lastCategory)
  }
}
