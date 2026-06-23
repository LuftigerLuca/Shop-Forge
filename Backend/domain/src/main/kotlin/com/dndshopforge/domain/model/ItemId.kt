package com.dndshopforge.domain.model

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.map
import com.dndshopforge.domain.validation.validate
import java.util.UUID

@JvmInline
value class ItemId private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): Result<ItemId> =
            validate(value) {
                (value.isNotBlank()) otherwise Problem("must not be blank", Problem.ProblemType.VALIDATION)
                (value.length <= 36) otherwise Problem("must not exceed 36 characters", Problem.ProblemType.VALIDATION)
            }.map { ItemId(it) }

        fun random() = ItemId(UUID.randomUUID().toString())
    }
}
