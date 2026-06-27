package com.dndshopforge.domain.item.model

import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.result.map
import com.dndshopforge.domain.shared.validation.validate

@JvmInline
value class ItemName private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): Result<ItemName> =
            validate(value) {
                (value.isNotBlank()) otherwise Problem("must not be blank", Problem.ProblemType.VALIDATION)
                (value.length <= 100) otherwise
                    Problem(
                        "must not exceed 100 characters",
                        Problem.ProblemType.VALIDATION,
                    )
            }.map { ItemName(it) }
    }
}
