package com.dndshopforge.domain.user.model

import com.dndshopforge.domain.shared.result.ErrorCode
import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.result.map
import com.dndshopforge.domain.shared.validation.validate

@JvmInline
value class UserName private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): Result<UserName> =
            validate(value) {
                (value.isNotBlank()) otherwise Problem(ErrorCode.USERNAME_BLANK, "Username must not be empty", Problem.ProblemType.VALIDATION)
                (value.length <= 36) otherwise
                    Problem(
                        ErrorCode.USERNAME_TOO_LONG,
                        "Username must not exceed 36 characters",
                        Problem.ProblemType.VALIDATION,
                    )
                (value.matches(Regex("[a-zA-Z0-9_.]+"))) otherwise
                    Problem(
                        ErrorCode.USERNAME_INVALID_CHARS,
                        "Username may only contain letters, numbers, underscores and periods",
                        Problem.ProblemType.VALIDATION,
                    )
            }.map { UserName(it) }
    }
}
