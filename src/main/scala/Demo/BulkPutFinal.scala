package Demo

import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.spark.HBaseContext
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object BulkPutFinal {
  def main(args: Array[String]): Unit = {
    val tableName = "TestWordCount"
    val columnFamily = "data"
    val qualifierName = "text"
    val sparkConf = new SparkConf().setAppName("HBaseBulkPutExample").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val conf = HBaseConfiguration.create();
    conf.addResource(new Path("/etc/hbase/conf/core-site.xml"));
    conf.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
    conf.set("hbase.zookeeper.quorum", "192.168.100.214,192.168.100.215,192.168.100.216")
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    conf.set("hbase.master", "192.168.100.215:60000")
    conf.setInt("timeout", 60000)
    val hbaseContext = new HBaseContext(sc, conf);

    val file: RDD[String] = sc.textFile("C:\\Users\\Cronium\\Desktop\\Вводная_BigData\\input_file_1.txt")

    def stringToPut(s: String): Put = {
      val put = new Put(Bytes.toBytes(s.hashCode))
      put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifierName), Bytes.toBytes(s))
      put
    }

    //file.foreach(line => hbaseContext.bulkPut(file, TableName.valueOf(tableName), stringToPut(line));

  }
}


