package org.example.data.enums

object SourceTypeEnum extends Enumeration {
    type SourceTypeEnum = Value

    val KAFKACONSUMER: Value = Value("KAFKACONSUMER")
    val KSTREAMS: Value = Value("KSTREAMS")
}
