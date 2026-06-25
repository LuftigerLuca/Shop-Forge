package com.dndshopforge.domain.model

import com.dndshopforge.domain.model.item.ItemId
import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.util.UUID

class ItemIdTest :
    StringSpec({

        "blank string should fail" {
            ItemId.of("") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "whitespace string should fail" {
            ItemId.of("   ") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "string exceeding 36 characters should fail" {
            ItemId.of("a".repeat(37)) shouldBe
                Result.Failure(
                    listOf(Problem("must not exceed 36 characters", Problem.ProblemType.VALIDATION)),
                )
        }

        "valid UUID string should succeed" {
            val uuid = UUID.randomUUID().toString()
            val result = ItemId.of(uuid)
            result.shouldBeInstanceOf<Result.Success<ItemId>>()
            result.data.value shouldBe uuid
        }

        "non-UUID non-blank string should succeed" {
            val result = ItemId.of("abc")
            result.shouldBeInstanceOf<Result.Success<ItemId>>()
            result.data.value shouldBe "abc"
        }
    })
