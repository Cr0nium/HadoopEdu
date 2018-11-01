##Задача 1.

###Условие 

- Написать WordCount на Spark. 
- Запустить локально на компьютере.
- Входной файл в приложении: input_file_1

###Решение 1 (Базавое)

```scala
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
```

####Результат 

https://yadi.sk/d/v86RhCAOmWM8ow

###Решение 2 (Улучшенное)
```scala
import org.apache.spark._
   
   object One {
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
```
####Результат 

 https://yadi.sk/d/QWiIQePiVTlBXg