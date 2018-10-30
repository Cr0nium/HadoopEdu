package RoadToBigData

import scala.io.StdIn

object combinerOne {
  def main(args: Array[String]): Unit = {
    var line = ""
    var (lastKey, sum, sumCount) = ("", 0, 0)
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      val subArray = line.trim.split("\t")
      val (key, value, count) = (subArray(0), subArray(1).split(";")(0).toInt, subArray(1).split(";")(1).toInt)
      if ((lastKey != "") && (lastKey != key)) {
        println(lastKey + "\t" + sum + ";" + sumCount)
        lastKey = key
        sum = value
        sumCount = 1
      } else {
        lastKey = key
        sum += value
        sumCount += count
      }
    }
    if (lastKey != "") println(lastKey + "\t" + sum + ";" + sumCount)
  }
}
