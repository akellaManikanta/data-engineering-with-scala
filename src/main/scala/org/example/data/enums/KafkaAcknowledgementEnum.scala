package org.example.data.enums

object KafkaAcknowledgementEnum extends Enumeration {

    type KafkaAcknowledgementEnum = Value

    val ZERO: Value = Value("0")
    val ONE: Value  = Value("1")
    val ALL: Value = Value("all")
}
