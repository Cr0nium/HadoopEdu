package Demo

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.Admin
import org.apache.hadoop.hbase.client.Connection
import org.apache.hadoop.hbase.client.ConnectionFactory


object HBaseSample {
  def main(args: Array[String]): Unit = {
    val conf = HBaseConfiguration.create
    try {
      val conn = ConnectionFactory.createConnection(conf)
      val hAdmin = conn.getAdmin
      val hTableDesc = new HTableDescriptor(TableName.valueOf("Customer"))
      hTableDesc.addFamily(new HColumnDescriptor("name"))
      hTableDesc.addFamily(new HColumnDescriptor("contactinfo"))
      hTableDesc.addFamily(new HColumnDescriptor("address"))
      hAdmin.createTable(hTableDesc)
      System.out.println("Table created Successfully...")
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}