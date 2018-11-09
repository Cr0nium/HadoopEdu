package Demo

import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.spark._


object HbaseReadOne {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("HBaseRead").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val conf = HBaseConfiguration.create()

    conf.set("hbase.master", "192.168.6.20:7180")
    conf.setInt("timeout", 120000)
    conf.set("hbase.zookeeper.quorum", "192.168.6.20")
    conf.set("zookeeper.znode.parent", "/hbase")
    val conn = ConnectionFactory.createConnection(conf)
    val admin = conn.getAdmin

    val hTableDesc = new HTableDescriptor(TableName.valueOf("Customer2"))
    hTableDesc.addFamily(new HColumnDescriptor("name"))
    hTableDesc.addFamily(new HColumnDescriptor("contactinfo"))
    hTableDesc.addFamily(new HColumnDescriptor("address"))
    admin.createTable(hTableDesc)

    sc.stop()
  }
}