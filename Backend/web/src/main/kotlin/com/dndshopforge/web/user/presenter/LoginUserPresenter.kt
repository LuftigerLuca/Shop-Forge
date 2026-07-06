package com.dndshopforge.web.user.presenter

import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.user.response.LoginUserResponse
import com.dndshopforge.web.shared.security.JwtService
import com.dndshopforge.web.user.mapper.LoginUserMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class LoginUserPresenter(
    private val jwtService: JwtService,
) {
    fun present(result: Result<LoginUserResponse>): ResponseEntity<*> =
        when (result) {
            is Result.Success -> {
                val token = jwtService.generateToken(result.data.id.value)
                ResponseEntity.ok(LoginUserMapper.toSchema(result.data, token))
            }

            is Result.Failure -> {
                val problem = result.problems.firstOrNull()
                val status = problem?.type?.toHttpStatus() ?: HttpStatus.INTERNAL_SERVER_ERROR
                ResponseEntity.status(status).body(mapOf("error" to (problem?.message ?: "Internal error")))
            }
        }
}

private fun Problem.ProblemType.toHttpStatus(): HttpStatus =
    when (this) {
        Problem.ProblemType.VALIDATION -> HttpStatus.BAD_REQUEST
        Problem.ProblemType.NOT_FOUND -> HttpStatus.NOT_FOUND
        Problem.ProblemType.UNAUTHORIZED -> HttpStatus.UNAUTHORIZED
        Problem.ProblemType.CONFLICT -> HttpStatus.CONFLICT
        Problem.ProblemType.INTERNAL, Problem.ProblemType.PERSISTENCE -> HttpStatus.INTERNAL_SERVER_ERROR
    }
