package Demo

import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark._

object HbaseTableCreate {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HBaseReadWrite").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val conf = HBaseConfiguration.create()

    conf.set("hbase.zookeeper.quorum", "192.168.100.214,192.168.100.215,192.168.100.216")
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    conf.set("hbase.master", "192.168.100.215:60000")
    conf.setInt("timeout", 60000)

    val conn = ConnectionFactory.createConnection(conf)
    val admin = conn.getAdmin
    val table = conn.getTable(TableName.valueOf(Bytes.toBytes("TestWordCount")))

    val hTableDesc = new HTableDescriptor(TableName.valueOf("TestWordCount"))
    hTableDesc.addFamily(new HColumnDescriptor("data"))
    admin.createTable(hTableDesc)

    sc.stop()
  }
}
