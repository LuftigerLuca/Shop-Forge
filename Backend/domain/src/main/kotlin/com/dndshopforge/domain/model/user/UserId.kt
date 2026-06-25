package com.dndshopforge.domain.model.user

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.map
import com.dndshopforge.domain.validation.validate
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
