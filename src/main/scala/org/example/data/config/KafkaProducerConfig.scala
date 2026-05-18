package org.example.data.config

import com.typesafe.config.Config
import org.example.data.enums.{KafkaAcknowledgementEnum, KafkaMessageSendStyle}
import org.example.data.enums.KafkaAcknowledgementEnum.KafkaAcknowledgementEnum
import org.example.data.enums.KafkaMessageSendStyle.KafkaMessageSendStyle

import java.util.Properties


case class KafkaProducerConfig(
                                bootstrapServers: String,
                                topicName: String,
                                acknowledgement: KafkaAcknowledgementEnum,
                                sendStyle: KafkaMessageSendStyle,
                                retries: Option[Int],
                                batchSize: Option[Int],
                                compressionType: Option[String],
                                clientId: Option[String],
                                retryBackoffMs: Option[Int],
                                deliveryTimeoutMs: Option[Int],
                                requestTimeoutMs: Option[Int],
                                lingerMs: Option[Int],
                                bufferMemory: Option[Int],
                                maxInFlightRequestsPerConnection: Option[Int],
                                enableIdempotence: Option[Boolean],
                                transactionalId: Option[String],
                                maxBlockMs: Option[Int],
                                metadataMaxAgeMs: Option[Int],
                                keySerializer: String = "org.apache.kafka.common.serialization.StringSerializer",
                                valueSerializer: String = "org.apache.kafka.common.serialization.StringSerializer"
                              ) extends TargetConfig {
    /**
     * Build and return source-specific properties.
     */
    override def getTargetConfig: Properties = {
        val props = new Properties()
        // Mandatory configs
        props.put("bootstrap.servers", bootstrapServers)
        props.put("key.serializer", keySerializer)
        props.put("value.serializer", valueSerializer)

        // Reliability
        props.put("acks", acknowledgement)
        props.put("retries", retries.toString)
        props.put("enable.idempotence", enableIdempotence.toString)

        // Performance tuning
        props.put("batch.size", batchSize.toString)
        props.put("linger.ms", lingerMs.toString)
        props.put("buffer.memory", bufferMemory.toString)
        props.put(
            "max.in.flight.requests.per.connection",
            maxInFlightRequestsPerConnection.toString
        )

        // Timeouts
        props.put("delivery.timeout.ms", deliveryTimeoutMs.toString)
        props.put("request.timeout.ms", requestTimeoutMs.toString)

        // Optional configs
        compressionType.foreach(value =>
            props.put("compression.type", value)
        )

        props
    }
}

object KafkaProducerConfig {
    def apply(config: Config): KafkaProducerConfig = {
        val producerConfig =
            config.getConfig("target.kafka.producer")

        KafkaProducerConfig(
            bootstrapServers = producerConfig.getString("bootstrapServers"),
            topicName = producerConfig.getString("topicName"),
            acknowledgement = if (producerConfig.hasPath("acknowledgement")) KafkaAcknowledgementEnum.withName(producerConfig.getString("acknowledgement").toUpperCase()) else KafkaAcknowledgementEnum.withName("ZERO"),
            sendStyle = if (producerConfig.hasPath("sendStyle")) KafkaMessageSendStyle.withName(producerConfig.getString("sendStyle").toUpperCase()) else KafkaMessageSendStyle.withName("FIRE_AND_FORGET"),
            retries = if (producerConfig.hasPath("retries")) Some(producerConfig.getInt("batchSize")) else None,
            batchSize = if (producerConfig.hasPath("batchSize")) Some(producerConfig.getInt("batchSize")) else None,
            compressionType = if (producerConfig.hasPath("compressionType")) Some(producerConfig.getString("compressionType")) else None,
            clientId = if (producerConfig.hasPath("clientId") && !producerConfig.getIsNull("clientId")) Some(producerConfig.getString("clientId")) else None,
            retryBackoffMs = if (producerConfig.hasPath("retryBackoffMs") && !producerConfig.getIsNull("retryBackoffMs")) Some(producerConfig.getInt("retryBackoffMs")) else None,
            deliveryTimeoutMs = if (producerConfig.hasPath("deliveryTimeoutMs") && !producerConfig.getIsNull("deliveryTimeoutMs")) Some(producerConfig.getInt("deliveryTimeoutMs")) else None,
            requestTimeoutMs = if (producerConfig.hasPath("requestTimeoutMs") && !producerConfig.getIsNull("requestTimeoutMs")) Some(producerConfig.getInt("requestTimeoutMs")) else None,
            lingerMs = if (producerConfig.hasPath("lingerMs") && !producerConfig.getIsNull("lingerMs")) Some(producerConfig.getInt("lingerMs")) else None,
            bufferMemory = if (producerConfig.hasPath("bufferMemory") && !producerConfig.getIsNull("bufferMemory")) Some(producerConfig.getInt("bufferMemory")) else None,
            maxInFlightRequestsPerConnection = if (producerConfig.hasPath("maxInFlightRequestsPerConnection") && !producerConfig.getIsNull("maxInFlightRequestsPerConnection")) Some(producerConfig.getInt("retryBackoffMs")) else None,
            enableIdempotence = if (producerConfig.hasPath("enableIdempotence") && !producerConfig.getIsNull("enableIdempotence")) Some(producerConfig.getBoolean("enableIdempotence")) else None,
            transactionalId = if (producerConfig.hasPath("transactionalId") && !producerConfig.getIsNull("transactionalId")) Some(producerConfig.getString("transactionalId")) else None,
            maxBlockMs = if (producerConfig.hasPath("maxBlockMs") && !producerConfig.getIsNull("maxBlockMs")) Some(producerConfig.getInt("maxBlockMs")) else None,
            metadataMaxAgeMs = if (producerConfig.hasPath("metadataMaxAgeMs") && !producerConfig.getIsNull("metadataMaxAgeMs")) Some(producerConfig.getInt("metadataMaxAgeMs")) else None,
        )
    }
}