package org.example.data.enums

object KafkaMessageSendStyle extends Enumeration {
  type KafkaMessageSendStyle = Value

  val "FIRE_AND_FORGET": Value = Value("FIRE_AND_FORGET")
  val "SYNCHRONOUS_SEND": Value = Value("SYNCHRONOUS_SEND")
  val "ASYNCHRONOUS_SEND": Value = Value("ASYNCHRONOUS_SEND")
}
