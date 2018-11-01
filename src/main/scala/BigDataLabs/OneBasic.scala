package BigDataLabs

import org.apache.spark._

object OneBasic {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Spark Program").setMaster("local")
    val spark = new SparkContext(conf)
    val file = spark.textFile("C:\\Users\\timur\\Desktop\\Вводная_BigData\\input_file_1.txt")
    val counts = file.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile("CountSaveFileBasic")
    spark.stop()
  }
}
