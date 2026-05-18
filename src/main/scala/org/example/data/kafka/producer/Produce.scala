package org.example.data.kafka.producer

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

abstract class Produce[K, V](topicName: String) extends LazyLogging {

  protected def acknowledgementType: String

  def send(producer: KafkaProducer[K, V], key: Option[K], value: V, partition: Option[Int] = None): Unit = {
    val kafkaKey = key.orNull.asInstanceOf[K]

    logger.info(s"All Acknowledgement send ${if (key.isDefined) "with key" else "without key"}")

    val producerRecord = partition match {
      case Some(p) => new ProducerRecord[K, V](topicName, p, kafkaKey, value)
      case None => new ProducerRecord[K, V](topicName, kafkaKey, value)
    }
    producer.send(producerRecord)
  }
}
