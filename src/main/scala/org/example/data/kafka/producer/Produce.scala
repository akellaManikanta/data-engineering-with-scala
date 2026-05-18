package org.example.data.kafka.producer

import org.apache.kafka.clients.producer.KafkaProducer

trait Produce[K, V] {
    def send(producer: KafkaProducer[K, V], key: K = None, value: V, partition: Int = null): Unit
}
