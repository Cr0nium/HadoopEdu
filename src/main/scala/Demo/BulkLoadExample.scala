package Demo

import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue, TableName}
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object BulkLoadExample {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HBaseRead").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val conf = HBaseConfiguration.create()

   /* conf.set("hbase.master", "192.168.6.20:7180")
    conf.setInt("timeout", 60000)
    conf.set("hbase.zookeeper.quorum", "192.168.6.21")

    val conn = ConnectionFactory.createConnection(conf)
    val admin = conn.getAdmin*/
    val table = "TestWordCount"
    val columnFamilyName = "data"
    val qualifierName = "text"

    val file = sc.textFile("C:\\Users\\Cronium\\Desktop\\Вводная_BigData\\input_file_1.txt")
    val transformed = file.map(f = v => {
      val key = Bytes.toBytes(v.hashCode())
      val kv = new KeyValue(key,
        Bytes.toBytes(columnFamilyName),
        Bytes.toBytes(qualifierName),
        Bytes.toBytes(v))
      (new ImmutableBytesWritable(key), kv)
    })

    transformed.saveAsTextFile("TransformedSaveFile")

    sc.stop()
  }
}
