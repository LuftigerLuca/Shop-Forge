package com.dndshopforge.bootstrap.context

import com.dndshopforge.application.shared.factory.UseCaseFactory
import com.dndshopforge.application.shared.security.PasswordHasher
import com.dndshopforge.domain.item.gateway.ItemGateway
import com.dndshopforge.domain.user.gateway.UserGateway
import com.dndshopforge.persistence.shared.factory.GatewayFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class UseCaseContext {
    @Bean
    fun useCaseFactory(
        gatewayFactory: GatewayFactory,
        passwordHasher: PasswordHasher,
    ) = UseCaseFactory(
        userGateway = gatewayFactory[UserGateway::class],
        itemGateway = gatewayFactory[ItemGateway::class],
        passwordHasher = passwordHasher,
    )
}
