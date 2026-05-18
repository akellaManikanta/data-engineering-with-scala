package org.example.data.kafka.producer

import org.example.data.enums.KafkaAcknowledgementEnum

import scala.annotation.tailrec

class NoAcknowledgement[K, V](topicName: String) extends Produce[K, V](topicName: String) {
    override protected def acknowledgementType: String = KafkaAcknowledgementEnum.withName("ZERO").toString
}

object NoAcknowledgement {
  @tailrec
  def apply[K, V](): NoAcknowledgement[K, V] = {
    NoAcknowledgement[K, V]()
  }
}