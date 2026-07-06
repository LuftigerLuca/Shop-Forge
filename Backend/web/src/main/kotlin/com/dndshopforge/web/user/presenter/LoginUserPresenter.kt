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
                val status = result.problems.toHttpStatus()
                ResponseEntity.status(status).body(mapOf(
                    "errors" to result.problems.map { p ->
                        mapOf("code" to p.code.name, "message" to p.message)
                    }
                ))
            }
        }
}

private fun List<Problem>.toHttpStatus(): HttpStatus {
    val statuses = mapNotNull { it.type?.toHttpStatus() }
    return when {
        HttpStatus.INTERNAL_SERVER_ERROR in statuses -> HttpStatus.INTERNAL_SERVER_ERROR
        HttpStatus.UNAUTHORIZED in statuses -> HttpStatus.UNAUTHORIZED
        HttpStatus.CONFLICT in statuses -> HttpStatus.CONFLICT
        HttpStatus.NOT_FOUND in statuses -> HttpStatus.NOT_FOUND
        else -> HttpStatus.BAD_REQUEST
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
