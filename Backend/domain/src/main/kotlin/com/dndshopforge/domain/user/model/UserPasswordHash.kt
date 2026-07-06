package com.dndshopforge.domain.user.model

import com.dndshopforge.domain.shared.result.ErrorCode
import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.result.map
import com.dndshopforge.domain.shared.validation.validate

@JvmInline
value class UserPasswordHash private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): Result<UserPasswordHash> =
            validate(value) {
                (value.isNotBlank()) otherwise
                    Problem(ErrorCode.HASH_BLANK, "Password hash must not be empty", Problem.ProblemType.VALIDATION)
                (value.length <= 128) otherwise
                    Problem(ErrorCode.HASH_TOO_LONG, "Password hash must not exceed 128 characters", Problem.ProblemType.VALIDATION)
                (value.length >= 32) otherwise
                    Problem(
                        ErrorCode.HASH_TOO_SHORT,
                        "Password hash must not be less than 32 characters",
                        Problem.ProblemType.VALIDATION,
                    )
            }.map { UserPasswordHash(it) }
    }
}
