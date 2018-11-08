name := "TestTwo"

version := "0.1"

scalaVersion := "2.11.8"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.3.2",
  "org.apache.hbase" % "hbase-server" % "1.2.2",
  "org.apache.hbase" % "hbase-common" % "1.2.2",
  "org.apache.hbase" % "hbase-client" % "1.2.2",
  "org.apache.hadoop" % "hadoop-common" % "2.7.1",
  "org.apache.hbase" % "hbase-rest" % "2.1.1",
  "com.hortonworks" % "shc-core" % "1.1.1-2.1-s_2.11"
)







