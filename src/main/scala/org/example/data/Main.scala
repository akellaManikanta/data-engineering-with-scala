package org.example.data

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import org.example.data.config._
import org.example.data.enums.{SourceTypeEnum, TargetConfigEnum}

object Main extends App with LazyLogging {

    logger.info("Starting the Data Pipeline.")

    val config = ConfigFactory.load()

    val sourceType =
        SourceTypeEnum.withName(
            config.getString("app.sourceType").toUpperCase
        )

    val targetType =
        TargetConfigEnum.withName(
            config.getString("app.targetType").toUpperCase
        )

    val sourceConfig: SourceConfig = {
        sourceType match {
            case SourceTypeEnum.KAFKACONSUMER =>
                KafkaConsumerConfig(config)
            case _ =>
                throw new ClassNotFoundException(
                    s"Source type $sourceType not implemented."
                )
        }
    }

    val targetConfig: TargetConfig = {
        targetType match {
            case TargetConfigEnum.KAFKAPRODUCER =>
                KafkaProducerConfig(config)
            case _ =>
                throw new ClassNotFoundException(
                    s"Target type $targetType not implemented."
                )
        }
    }

    logger.info(s"Source Configuration type is ${sourceConfig.getClass.getSimpleName}")
    logger.info(s"Target Configuration type is ${targetConfig.getClass.getSimpleName}")

    logger.info("Completed the Data Pipeline.")
}