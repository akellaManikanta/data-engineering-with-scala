name := "data-engineering-with-scala"

version := "0.1"

scalaVersion := "2.13.18"

libraryDependencies ++= Seq(

  // Kafka-client and Kafka-Streams
  "org.apache.kafka" % "kafka-clients" % "3.7.0",
  "org.apache.kafka" %% "kafka-streams-scala" % "3.7.0",

  //spark
  "org.apache.spark" %% "spark-sql" % "3.5.1",

  // Logging
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "ch.qos.logback" % "logback-classic" % "1.5.18",

  // Faker Data generation Library
  "net.datafaker" % "datafaker" % "2.2.2"
)