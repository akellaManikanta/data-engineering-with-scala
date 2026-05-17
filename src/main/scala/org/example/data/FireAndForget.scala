//package org.example.data
//
//import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
//import org.example.data.Main.props
//
//import java.util.Properties
//
//class FireAndForget {
//    def send(properties: Properties) = {
//        // Create producer
//        val producer = new KafkaProducer[String, String](properties)
//
//        val topic = "simple-scala-raw"
//
//        for (i <- 1 to 5000) {
//            val key = s"key-$i"
//            val value = s"Manikanta-$i"
//
//            val record = new ProducerRecord[String, String](topic, key, value)
//            producer.send(record)
//            print(record)
//        }
//    }
//}
