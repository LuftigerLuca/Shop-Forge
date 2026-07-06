package com.dndshopforge.bootstrap.context

import com.dndshopforge.domain.shared.gateway.Gateway
import com.dndshopforge.persistence.shared.factory.GatewayFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class GatewayContext {
    @Bean
    fun gatewayFactory(gatewayAdapters: Set<Gateway>) = GatewayFactory(gatewayAdapters)
}
