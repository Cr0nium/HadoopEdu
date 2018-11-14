package Demo

import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.spark._
import org.apache.spark.rdd.RDD


object HbaseReadOne {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("HBaseRead").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val conf = HBaseConfiguration.create()

    conf.set("hbase.master", "192.168.6.20:7180")
    conf.setInt("timeout", 60000)
    conf.set("hbase.zookeeper.quorum", "192.168.6.21")

    val conn = ConnectionFactory.createConnection(conf)
    val admin = conn.getAdmin
    val table = conn.getTable(TableName.valueOf(Bytes.toBytes("TestWordCount")))
    val file: RDD[String] = sc.textFile("C:\\Users\\Cronium\\Desktop\\Вводная_BigData\\input_file_1.txt")
    getClass.getResource("input_file_1.txt")
    scala.io.Source.fromFile("input_file_1.txt").getLines()




    /* =================  CREATE TABLE "TestWordCount"  =======================
    val hTableDesc = new HTableDescriptor(TableName.valueOf("TestWordCount"))
    hTableDesc.addFamily(new HColumnDescriptor("data"))
    admin.createTable(hTableDesc)
    */

    var put = new Put(Bytes.toBytes("row3"))
    put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("text"), Bytes.toBytes(file.toString()))
    table.put(put)

    val result = table.get(new Get(Bytes.toBytes("row2")))
    val r1 = result.getValue(Bytes.toBytes("data"), Bytes.toBytes("text"))
    val resultText = result.getValue(Bytes.toBytes("data"), Bytes.toBytes("text"))
    val newFile = Bytes.toString(r1)
    println(newFile)

/*
    val counts = newFile.flatMap(_.split("\\s+"))
      .filter(_.matches("^[А-яA-Za-z]+[,\\.]?$"))
      .map(word => if (word.matches("^.*[,\\.]$")) (word.dropRight(1), 1) else (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile("CountSaveFile")
*/
    sc.stop()
  }
}