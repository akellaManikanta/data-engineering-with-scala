package org.example.data.config

import java.util.Properties

/**
 * Generic contract for all source configurations.
 * Each source implementation should return its own Properties object.
 */
trait SourceConfig {

    /**
     * Build and return source-specific properties.
     */
    def getSourceProperties: Properties
}