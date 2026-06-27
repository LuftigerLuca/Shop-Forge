package com.dndshopforge.domain.user.model

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
                (value.isNotBlank()) otherwise Problem("must not be blank", Problem.ProblemType.VALIDATION)
                (value.length <= 36) otherwise
                    Problem(
                        "must not exceed 36 characters",
                        Problem.ProblemType.VALIDATION,
                    )
                (value.matches(Regex("[a-zA-Z0-9_.]+"))) otherwise
                    Problem(
                        "must contain only letters, numbers, underscores and periods",
                        Problem.ProblemType.VALIDATION,
                    )
            }.map { UserName(it) }
    }
}
