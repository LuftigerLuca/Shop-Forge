package com.dndshopforge.domain.user.model

import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.result.map
import com.dndshopforge.domain.shared.validation.validate
import java.util.UUID

@JvmInline
value class UserId private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): Result<UserId> =
            validate(value) {
                (value.isNotBlank()) otherwise Problem("must not be blank", Problem.ProblemType.VALIDATION)
                (value.length <= 36) otherwise Problem("must not exceed 36 characters", Problem.ProblemType.VALIDATION)
            }.map { UserId(it) }

        fun random() = of(UUID.randomUUID().toString())
    }
}
