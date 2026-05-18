package org.example.data.kafka.producer

import org.apache.kafka.clients.producer.KafkaProducer

class AllISRAcknowledgement[K, V] extends Produce[K, V]{

    override def send(producer: KafkaProducer[K, V], key: K, value: V, partition: Int): Unit = {
        key match {
            case None => println("All Broker Acknowledgement send without key")
            case _ => println("All Broker Acknowledgement send with key")
        }
    }
}

object AllISRAcknowledgement {
    def apply[K, V](): AllISRAcknowledgement[K, V] =
        new AllISRAcknowledgement[K, V]()
}