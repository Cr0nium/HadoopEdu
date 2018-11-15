package Demo

import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.spark.HBaseContext
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.{SparkConf, SparkContext}


object BulkPutExample {
  def main(args: Array[String]): Unit = {
    val tableName = "TestWordCount"
    val columnFamily = "data"
    val sparkConf = new SparkConf().setAppName("HBaseBulkPutExample").setMaster("local")
    val sc = new SparkContext(sparkConf)

    //This is making a RDD of
    //(RowKey, columnFamily, columnQualifier, value)
    val rdd = sc.parallelize(Array(
      (Bytes.toBytes("1"), Array((Bytes.toBytes(columnFamily), Bytes.toBytes("1"), Bytes.toBytes("1")))),
      (Bytes.toBytes("2"), Array((Bytes.toBytes(columnFamily), Bytes.toBytes("1"), Bytes.toBytes("2")))),
      (Bytes.toBytes("3"), Array((Bytes.toBytes(columnFamily), Bytes.toBytes("1"), Bytes.toBytes("3")))),
      (Bytes.toBytes("4"), Array((Bytes.toBytes(columnFamily), Bytes.toBytes("1"), Bytes.toBytes("4")))),
      (Bytes.toBytes("5"), Array((Bytes.toBytes(columnFamily), Bytes.toBytes("1"), Bytes.toBytes("5"))))
    )
    )
    //Create the HBase config like you normally would  then
    //Pass the HBase configs and SparkContext to the HBaseContext
    val conf = HBaseConfiguration.create();
    conf.addResource(new Path("/etc/hbase/conf/core-site.xml"));
    conf.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
    conf.set("hbase.master", "192.168.6.20:7180")
    conf.setInt("timeout", 60000)
    conf.set("hbase.zookeeper.quorum", "192.168.6.21")
    val hbaseContext = new HBaseContext(sc, conf);

    //Now give the rdd, table name, and a function that will convert a RDD record to a put, and finally
    // A flag if you want the puts to be batched
    hbaseContext.bulkPut[(Array[Byte], Array[(Array[Byte], Array[Byte], Array[Byte])])](rdd,
      TableName.valueOf(tableName),
      //This function is really important because it allows our source RDD to have data of any type
      // Also because puts are not serializable
      (putRecord) => {
        val put = new Put(putRecord._1)
        putRecord._2.foreach((putValue) => put.addColumn(putValue._1, putValue._2, putValue._3))
        put
      }
    );
  }
}


