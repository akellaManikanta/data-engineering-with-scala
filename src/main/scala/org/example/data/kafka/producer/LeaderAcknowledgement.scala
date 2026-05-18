package org.example.data.kafka.producer

import org.apache.kafka.clients.producer.KafkaProducer

import scala.annotation.tailrec

class LeaderAcknowledgement[K, V] extends Produce[K, V] {

    override def send(producer: KafkaProducer[K, V], key: K, value: V, partition: Int): Unit = {
        key match {
            case None => println("Leader Acknowledgement send without key")
            case _ => println("Leader Acknowledgement send with key")
        }
    }
}

object LeaderAcknowledgement {
    @tailrec
    def apply[K, V](): LeaderAcknowledgement[K, V]= {
        LeaderAcknowledgement[K, V]()
    }
}