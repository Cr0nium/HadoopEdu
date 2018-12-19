package Demo

import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.{SparkConf, SparkContext}

object HbaseConnectKerberos {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HBaseRead").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val conf = HBaseConfiguration.create()

    conf.set("hbase.zookeeper.quorum", "192.168.100.214,192.168.100.215,192.168.100.216")
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    conf.set("hadoop.security.authentication", "kerberos")
    conf.set("hbase.security.authentication", "kerberos")
    conf.set("hbase.cluster.distributed", "true")

    //conf.set("hbase.rpc.protection", "authentication")

    conf.set("hbase.master.kerberos.principal", "krbtgt/CDH-SBX.LOCAL@CDH-SBX.LOCAL")
    conf.set("hbase.master.keytab.file", "src/hbase.service.keytab")
    conf.set("hbase.regionserver.kerberos.principal", "krbtgt/CDH-SBX.LOCAL@CDH-SBX.LOCAL")
    conf.set("hbase.regionserver.keytab.file", "src/hbase.service.keytab")

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
