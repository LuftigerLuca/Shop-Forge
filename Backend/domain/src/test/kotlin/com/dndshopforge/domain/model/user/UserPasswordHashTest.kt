package com.dndshopforge.domain.model.user

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class UserPasswordHashTest :
    StringSpec({

        "blank string should fail" {
            UserPasswordHash.of("") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "whitespace string should fail" {
            UserPasswordHash.of("   ") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "string exceeding 128 characters should fail" {
            UserPasswordHash.of("a".repeat(129)) shouldBe
                Result.Failure(
                    listOf(Problem("must not exceed 128 characters", Problem.ProblemType.VALIDATION)),
                )
        }

        "string less than 23 characters should fail" {
            UserPasswordHash.of("a".repeat(22)) shouldBe
                Result.Failure(
                    listOf(Problem("must not be less than 32 characters", Problem.ProblemType.VALIDATION)),
                )
        }

        "valid hash at minimum length should succeed" {
            val result = UserPasswordHash.of("a".repeat(23))
            result.shouldBeInstanceOf<Result.Success<UserPasswordHash>>()
            result.data.value shouldBe "a".repeat(23)
        }

        "valid hash at maximum length should succeed" {
            val result = UserPasswordHash.of("a".repeat(128))
            result.shouldBeInstanceOf<Result.Success<UserPasswordHash>>()
            result.data.value shouldBe "a".repeat(128)
        }

        "valid hash with special characters should succeed" {
            val hash = "abc123!@#\$%^&*()_+-=[]{}|;':\",./<>?`~abc"
            require(hash.length in 23..128)
            val result = UserPasswordHash.of(hash)
            result.shouldBeInstanceOf<Result.Success<UserPasswordHash>>()
            result.data.value shouldBe hash
        }
    })
