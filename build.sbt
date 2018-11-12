name := "TestTwo"

version := "0.1"

scalaVersion := "2.11.8"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "1.6.3"  ,
  "org.apache.hbase" % "hbase-server" % "2.1.1",
  "org.apache.hbase" % "hbase-common" % "2.1.1",
  "org.apache.hbase" % "hbase-client" % "2.1.1",
  "org.apache.hbase" % "hbase-rest" % "2.1.1",
  "org.apache.hbase" % "hbase-mapreduce" % "2.1.1",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.5",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.11",
  "org.apache.hbase" % "hbase-spark" % "2.0.0-alpha2"
)







