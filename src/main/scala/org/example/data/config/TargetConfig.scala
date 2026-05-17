package org.example.data.config

import java.util.Properties

/**
 * Generic contract for all target configurations.
 * Each target implementation should return its own Properties object.
 */
trait TargetConfig {
    /**
     * Build and return source-specific properties.
     */
    def getTargetConfig: Properties
}
