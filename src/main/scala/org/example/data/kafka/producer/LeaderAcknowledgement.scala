package org.example.data.kafka.producer

import org.example.data.enums.KafkaAcknowledgementEnum

import scala.annotation.tailrec

class LeaderAcknowledgement[K, V](topicName: String) extends Produce[K, V](topicName: String) {
    override protected def acknowledgementType: String = KafkaAcknowledgementEnum.withName("ONE").toString

}

object LeaderAcknowledgement {
  @tailrec
  def apply[K, V](): LeaderAcknowledgement[K, V] = {
    LeaderAcknowledgement[K, V]()
  }
}