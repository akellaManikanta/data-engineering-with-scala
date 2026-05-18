package org.example.data.kafka.producer

import org.example.data.enums.KafkaAcknowledgementEnum

class AllISRAcknowledgement[K, V](topicName: String) extends Produce[K, V](topicName: String) {
    override protected def acknowledgementType: String = KafkaAcknowledgementEnum.withName("ALL").toString

}

object AllISRAcknowledgement {
  def apply[K, V](topic: String): AllISRAcknowledgement[K, V] =
    new AllISRAcknowledgement[K, V](topic)
}