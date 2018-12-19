package Demo

import org.apache.hadoop.hbase.client.{Get, Put, ResultScanner, Scan}
import org.apache.hadoop.hbase.rest.client.{Client, RemoteHTable}
import org.apache.hadoop.hbase.util.Bytes


object LabTwo {
  def main(args: Array[String]) {
    val hbaseCluster  = new org.apache.hadoop.hbase.rest.client.Cluster()
    hbaseCluster.add("192.168.6.20", 7180)
    val restClient = new Client(hbaseCluster)
    val table =  new RemoteHTable(restClient, "TestWordCount")
    println("connected...")
    var p = new Put(Bytes.toBytes("row1"))
    p.addColumn(Bytes.toBytes("Words"), Bytes.toBytes("NAME"),Bytes.toBytes("raju"))
    p.addColumn(Bytes.toBytes("Words"), Bytes.toBytes("COURSE"),Bytes.toBytes("SCALA"))
    p.addColumn(Bytes.toBytes("Words"), Bytes.toBytes("YEAR"),Bytes.toBytes("2017"))
    table.put(p)

    val scan = new Scan()
    val scanner : ResultScanner = table.getScanner(scan)
    println("got scanner...")
    val g = new Get(Bytes.toBytes("row1"))
    val result = table.get(g)

    val name = Bytes.toString(result.getValue(Bytes.toBytes("0"),Bytes.toBytes("NAME")))
    val course = Bytes.toString(result.getValue(Bytes.toBytes("0"),Bytes.toBytes("COURSE")))
    val year = Bytes.toString(result.getValue(Bytes.toBytes("0"),Bytes.toBytes("YEAR")))

    println("row1 " + "name: " + name + " course: " + course + " year:" + year);

  }
}
