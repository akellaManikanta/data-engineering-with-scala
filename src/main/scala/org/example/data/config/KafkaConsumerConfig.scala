package org.example.data.config

import com.typesafe.config.Config

import java.util.Properties

case class KafkaConsumerConfig(
                                  bootstrapServers: String,
                                  groupId: String,
                                  keyDeserializer: String = "org.apache.kafka.common.serialization.StringDeserializer",
                                  valueDeserializer: String = "org.apache.kafka.common.serialization.StringDeserializer",
                                  topicName: String,
                                  // Optional Parameters
                                  enableAutoCommit: Option[Boolean],
                                  autoOffsetReset: Option[String],
                                  maxPollRecords: Option[Int],
                                  sessionTimeoutMs: Option[Int],
                                  heartbeatIntervalMs: Option[Int],
                                  maxPollIntervalMs: Option[Int],
                                  fetchMinBytes: Option[Int],
                                  fetchMaxWaitMs: Option[Int],
                                  requestTimeoutMs: Option[Int],
                                  retryBackoffMs: Option[Int],
                                  isolationLevel: Option[String]
                              ) extends SourceConfig {

    override def getSourceProperties: Properties = {
        val props = new Properties()

        props.put("bootstrap.servers", bootstrapServers)
        props.put("group.id", groupId)
        props.put("subscribe", topicName)

        autoOffsetReset.foreach(value =>
            props.put("auto.offset.reset", value)
        )

        enableAutoCommit.foreach(value =>
            props.put("enable.auto.commit", value.toString)
        )

        props
    }
}

object KafkaConsumerConfig {
    def apply(config: Config): KafkaConsumerConfig = {
        val consumerConfig =
            config.getConfig("source.kafka.consumer")
        KafkaConsumerConfig(
            bootstrapServers = consumerConfig.getString("bootstrap.servers"),
            groupId = consumerConfig.getString("groupId"),
            topicName = consumerConfig.getString("topicName"),
            // Optional Parameters
            enableAutoCommit = if (consumerConfig.hasPath("enableAutoCommit") && !consumerConfig.getIsNull("enableAutoCommit")) Some(consumerConfig.getBoolean("enableAutoCommit")) else None,
            autoOffsetReset = if (consumerConfig.hasPath("autoOffsetReset") && !consumerConfig.getIsNull("autoOffsetReset")) Some(consumerConfig.getString("autoOffsetReset")) else None,
            maxPollRecords = if (consumerConfig.hasPath("maxPollRecords") && !consumerConfig.getIsNull("maxPollRecords")) Some(consumerConfig.getInt("maxPollRecords")) else None,
            sessionTimeoutMs = if (consumerConfig.hasPath("sessionTimeoutMs") && !consumerConfig.getIsNull("sessionTimeoutMs")) Some(consumerConfig.getInt("sessionTimeoutMs")) else None,
            heartbeatIntervalMs = if (consumerConfig.hasPath("heartbeatIntervalMs") && !consumerConfig.getIsNull("heartbeatIntervalMs")) Some(consumerConfig.getInt("heartbeatIntervalMs")) else None,
            maxPollIntervalMs = if (consumerConfig.hasPath("maxPollIntervalMs") && !consumerConfig.getIsNull("maxPollIntervalMs")) Some(consumerConfig.getInt("maxPollIntervalMs")) else None,
            fetchMinBytes = if (consumerConfig.hasPath("fetchMinBytes") && !consumerConfig.getIsNull("fetchMinBytes")) Some(consumerConfig.getInt("fetchMinBytes")) else None,
            fetchMaxWaitMs = if (consumerConfig.hasPath("fetchMaxWaitMs") && !consumerConfig.getIsNull("fetchMaxWaitMs")) Some(consumerConfig.getInt("fetchMaxWaitMs")) else None,
            requestTimeoutMs = if (consumerConfig.hasPath("requestTimeoutMs") && !consumerConfig.getIsNull("requestTimeoutMs")) Some(consumerConfig.getInt("requestTimeoutMs")) else None,
            retryBackoffMs = if (consumerConfig.hasPath("retryBackoffMs") && !consumerConfig.getIsNull("retryBackoffMs")) Some(consumerConfig.getInt("retryBackoffMs")) else None,
            isolationLevel = if (consumerConfig.hasPath("isolationLevel") && !consumerConfig.getIsNull("isolationLevel")) Some(consumerConfig.getString("isolationLevel")) else None
        )
    }
}
