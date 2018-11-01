package BigDataLabs

import org.apache.spark._

object OneExtended {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Spark Program").setMaster("local")
    val spark = new SparkContext(conf)
    val file = spark.textFile("C:\\Users\\timur\\Desktop\\Вводная_BigData\\input_file_1.txt")
    val counts = file.flatMap(_.split("\\s+"))
      .filter(_.matches("^[А-яA-Za-z]+[,\\.]?$"))
      .map(word => if (word.matches("^.*[,\\.]$")) (word.dropRight(1), 1) else (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile("CountSaveFile")
    spark.stop()
  }
}
