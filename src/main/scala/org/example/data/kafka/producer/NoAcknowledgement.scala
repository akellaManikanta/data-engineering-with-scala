package org.example.data.kafka.producer

import org.apache.kafka.clients.producer.KafkaProducer

import scala.annotation.tailrec

class NoAcknowledgement[K, V] extends Produce[K, V] {
    override def send(producer: KafkaProducer[K, V], key: K = None, value: V, partition: Int = null): Unit = {
        key match {
            case None => println("Fire and Forget send without key")
            case _ => println("Fire and Forget send with key")
        }
    }
}

object NoAcknowledgement {
    @tailrec
    def apply[K, V](): NoAcknowledgement[K, V]= {
        NoAcknowledgement[K, V]()
    }
}