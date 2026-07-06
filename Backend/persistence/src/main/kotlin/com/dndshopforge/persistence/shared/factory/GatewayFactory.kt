package com.dndshopforge.persistence.shared.factory

import com.dndshopforge.domain.shared.gateway.Gateway
import kotlin.reflect.KClass

class GatewayFactory(
    private val gatewayAdapters: Set<Gateway>,
) {
    @Suppress("UNCHECKED_CAST")
    fun <T : Gateway> make(type: KClass<out T>): T =
        gatewayAdapters.singleOrNull { type.isInstance(it) } as? T
            ?: error("No Gateway registered for ${type.simpleName}")

    operator fun <T : Gateway> get(type: KClass<out T>): T = make(type)
}
