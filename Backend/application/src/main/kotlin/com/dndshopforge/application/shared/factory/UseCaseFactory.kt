package com.dndshopforge.application.shared.factory

import com.dndshopforge.application.shared.security.PasswordHasher
import com.dndshopforge.application.user.usecase.LoginUserUseCaseImpl
import com.dndshopforge.domain.item.gateway.ItemGateway
import com.dndshopforge.domain.shared.usecase.UseCase
import com.dndshopforge.domain.user.gateway.UserGateway
import com.dndshopforge.domain.user.usecase.LoginUserUseCase
import kotlin.reflect.KClass

class UseCaseFactory(
    userGateway: UserGateway,
    itemGateway: ItemGateway,
    passwordHasher: PasswordHasher,
) {
    private val useCases: Map<KClass<out UseCase<*, *>>, UseCase<*, *>> =
        mapOf(
            LoginUserUseCase::class to
                LoginUserUseCaseImpl(
                    gateway = userGateway,
                    hasher = passwordHasher,
                ),
        )

    @Suppress("UNCHECKED_CAST")
    fun <T : UseCase<*, *>> make(useCase: KClass<out T>): T =
        useCases[useCase] as? T
            ?: error("No UseCase registered for ${useCase.simpleName}")

    operator fun <T : UseCase<*, *>> get(useCase: KClass<out T>): T = make(useCase)
}
