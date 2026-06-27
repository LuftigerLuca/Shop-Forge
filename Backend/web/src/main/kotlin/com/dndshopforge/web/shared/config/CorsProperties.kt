package com.dndshopforge.web.shared.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "security.cors")
class CorsProperties {
    var allowedOrigins: List<String> = emptyList()
}
