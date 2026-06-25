package com.dndshopforge.domain.model.user

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.map
import com.dndshopforge.domain.validation.validate

@JvmInline
value class UserPasswordHash private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): Result<UserPasswordHash> =
            validate(value) {
                (value.isNotBlank()) otherwise Problem("must not be blank", Problem.ProblemType.VALIDATION)
                (value.length <= 128) otherwise Problem("must not exceed 128 characters", Problem.ProblemType.VALIDATION)
                (value.length >= 23) otherwise
                    Problem(
                        "must not be less than 32 characters",
                        Problem.ProblemType.VALIDATION,
                    )
            }.map { UserPasswordHash(it) }
    }
}
