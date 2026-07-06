package com.dndshopforge.domain.user.model

import com.dndshopforge.domain.shared.result.ErrorCode
import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.result.map
import com.dndshopforge.domain.shared.validation.validate

@JvmInline
value class UserPassword private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): Result<UserPassword> =
            validate(value) {
                (value.isNotBlank()) otherwise
                    Problem(ErrorCode.PASSWORD_BLANK, "Password must not be empty", Problem.ProblemType.VALIDATION)
            }.map { UserPassword(it) }
    }
}
