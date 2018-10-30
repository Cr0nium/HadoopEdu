import scala.collection.mutable
import scala.io.StdIn

object reducer {
  def main(args: Array[String]) {
    var line = ""

    val result = new mutable.HashMap[String, mutable.HashSet[String]]()
    while ( {
      line = StdIn.readLine()
      line != null
    }) {
      val (key, category) = (line.trim.split("\t")(0), line.trim.split("\t")(1))
      if (key != "") {
        if (!result.contains(category))
          result.put(category, mutable.HashSet())
        result(category).add(key)
      }
    }
    result.foreach(it => println(it._1 + "\t" + it._2.size))
  }
}