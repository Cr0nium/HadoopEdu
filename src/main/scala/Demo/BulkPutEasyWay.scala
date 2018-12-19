package Demo

import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.spark.HBaseContext
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue, TableName}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object BulkPutEasyWay {
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
    val transformed = file.map(v => {
      (Bytes.toBytes(v.hashCode()), Array((Bytes.toBytes(columnFamily), Bytes.toBytes(qualifierName), Bytes.toBytes(v))))
    })

    hbaseContext.bulkPut[(Array[Byte], Array[(Array[Byte], Array[Byte], Array[Byte])])](transformed,
      TableName.valueOf(tableName),
      (putRecord) => {
        val put = new Put(putRecord._1)
        putRecord._2.foreach((putValue) => put.addColumn(putValue._1, putValue._2, putValue._3))
        put
      }
    );
    sc.stop()
  }
}


