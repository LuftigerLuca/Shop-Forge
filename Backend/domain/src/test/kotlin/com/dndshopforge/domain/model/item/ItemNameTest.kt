package com.dndshopforge.domain.model

import com.dndshopforge.domain.model.item.ItemName
import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class ItemNameTest :
    StringSpec({

        "blank string should fail" {
            ItemName.of("") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "whitespace string should fail" {
            ItemName.of("   ") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "string exceeding 100 characters should fail" {
            ItemName.of("a".repeat(101)) shouldBe
                Result.Failure(
                    listOf(Problem("must not exceed 100 characters", Problem.ProblemType.VALIDATION)),
                )
        }

        "valid name should succeed" {
            val result = ItemName.of("Excalibur")
            result.shouldBeInstanceOf<Result.Success<ItemName>>()
            result.data.value shouldBe "Excalibur"
        }
    })
