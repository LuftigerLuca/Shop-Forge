package com.dndshopforge.domain.model.item

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.map
import com.dndshopforge.domain.validation.validate

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
