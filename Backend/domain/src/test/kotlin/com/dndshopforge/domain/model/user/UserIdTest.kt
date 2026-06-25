package com.dndshopforge.domain.model.user

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.util.UUID

class UserIdTest :
    StringSpec({

        "blank string should fail" {
            UserId.of("") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "whitespace string should fail" {
            UserId.of("   ") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "string exceeding 36 characters should fail" {
            UserId.of("a".repeat(37)) shouldBe
                Result.Failure(
                    listOf(Problem("must not exceed 36 characters", Problem.ProblemType.VALIDATION)),
                )
        }

        "valid UUID string should succeed" {
            val uuid = UUID.randomUUID().toString()
            val result = UserId.of(uuid)
            result.shouldBeInstanceOf<Result.Success<UserId>>()
            result.data.value shouldBe uuid
        }

        "non-UUID non-blank string should succeed" {
            val result = UserId.of("custom-id-123")
            result.shouldBeInstanceOf<Result.Success<UserId>>()
            result.data.value shouldBe "custom-id-123"
        }
    })
