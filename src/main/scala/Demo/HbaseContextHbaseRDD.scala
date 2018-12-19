package Demo

import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.client.{Get, Put, Scan}
import org.apache.hadoop.hbase.spark.HBaseContext
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue, TableName}
import org.apache.spark.{SparkConf, SparkContext}

object HbaseContextHbaseRDD {
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

    var scan = new Scan()
    scan.setCaching(100)
    var getRdd = hbaseContext.hbaseRDD(TableName.valueOf(tableName), scan)

    getRdd.collect().foreach(v => println(Bytes.toString(v._1.get())))
   // println("Length: " + getRdd.map(r => r._1.copyBytes()).collect().length);


    sc.stop()
  }
}


