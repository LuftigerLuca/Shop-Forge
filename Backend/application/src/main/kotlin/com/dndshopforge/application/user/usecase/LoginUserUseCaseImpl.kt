package com.dndshopforge.application.user.usecase

import com.dndshopforge.application.shared.security.PasswordHasher
import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.result.getOrNull
import com.dndshopforge.domain.user.gateway.UserGateway
import com.dndshopforge.domain.user.request.LoginUserRequest
import com.dndshopforge.domain.user.response.LoginUserResponse
import com.dndshopforge.domain.user.usecase.LoginUserUseCase

internal class LoginUserUseCaseImpl(
    private val gateway: UserGateway,
    private val hasher: PasswordHasher,
) : LoginUserUseCase {
    override fun execute(request: LoginUserRequest): Result<LoginUserResponse> {
        val user =
            gateway.findByUsername(request.username).getOrNull()
                ?: return Result.Failure(listOf(Problem("User not found", Problem.ProblemType.NOT_FOUND)))

        if (!hasher.verify(request.password.value, user.passwordHash.value)) {
            return Result.Failure(listOf(Problem("Invalid password", Problem.ProblemType.UNAUTHORIZED)))
        }

        val response =
            LoginUserResponse(
                id = user.id,
            )
        return Result.Success(response)
    }
}
