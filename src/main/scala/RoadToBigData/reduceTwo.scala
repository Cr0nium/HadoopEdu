package RoadToBigData

import scala.io.StdIn

object reduceTwo {
  def main(args: Array[String]): Unit = {
    var line = ""
    var (lastKey, sum, count) = ("", 0, 0)
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      val subArray = line.trim.split("\t")
      val (key, value) = (subArray(0), subArray(1).toInt)
      if ((lastKey != "") && (lastKey != key)) {
        println(lastKey + "\t" + sum/count.toInt)
        lastKey = key
        sum = value
        count = 1
      } else {
        lastKey = key
        sum += value
        count += 1
      }
    }
    if (lastKey != "") println(lastKey + "\t" + sum/count.toInt)
  }
}
