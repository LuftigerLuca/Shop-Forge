package com.dndshopforge.bootstrap

import com.dndshopforge.web.shared.config.CorsProperties
import com.dndshopforge.web.shared.config.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(
    basePackages = [
        "com.dndshopforge.persistence",
        "com.dndshopforge.bootstrap",
    ],
)
@EnableJpaRepositories(basePackages = ["com.dndshopforge.persistence"])
@EntityScan(basePackages = ["com.dndshopforge.persistence"])
@EnableConfigurationProperties(
    value = [
        CorsProperties::class,
        JwtProperties::class,
    ],
)
open class DnDForgeApplication

fun main(args: Array<String>) {
    runApplication<DnDForgeApplication>(*args)
}
