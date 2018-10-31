package RoadToBigData

import scala.math.random
import org.apache.spark._

object TestScala {

def main(args: Array[String]) {
  val conf = new SparkConf().setAppName("Spark Program").setMaster("local[2]").set("spark.executor.memory","1g")
  val spark = new SparkContext(conf)
  val n = if (args.length > 0) args(0).toInt else 100000
  val count = spark.parallelize(Range(1, n, 1)).map { i =>
    val x = random * 2 - 1          /* random возвращает случайное число из [0;1) */
    val y = random * 2 - 1
    if (x*x + y*y < 1) 1 else 0
  }.reduce(_ + _)
  println(4.0 * count / n)
  spark.stop()

}
}
