package RoadToBigData

import scala.io.StdIn

object mapperOne {
  def main(args: Array[String]) {
    var line = ""
    while ( {
      line = StdIn.readLine();
      line != null
    }) {
      for (token <- line.trim.split(" ")) if (token != "") println(token + "\t1")
    }
  }
}
