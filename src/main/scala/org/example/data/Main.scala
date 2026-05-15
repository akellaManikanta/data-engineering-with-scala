package org.example.data

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

object Main extends App  with LazyLogging{

    val config = ConfigFactory.load()

    val appName = config.getString("app.name")
    val version = config.getString("app.version")

    val bootstrapServers =
        config.getString("kafka.bootstrapServers")

    logger.info(appName)
    logger.info(version)
    logger.info(bootstrapServers)

}
