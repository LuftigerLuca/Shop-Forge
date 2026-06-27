package com.dndshopforge.domain.user.model

import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class UserNameTest :
    StringSpec({

        "blank string should fail" {
            UserName.of("") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "whitespace string should fail" {
            UserName.of("   ") shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "string exceeding 36 characters should fail" {
            UserName.of("a".repeat(37)) shouldBe
                Result.Failure(
                    listOf(
                        Problem("must not exceed 36 characters", Problem.ProblemType.VALIDATION),
                    ),
                )
        }

        "string with invalid characters should fail" {
            UserName.of("abc!@#") shouldBe
                Result.Failure(
                    listOf(
                        Problem(
                            "must contain only letters, numbers, underscores and periods",
                            Problem.ProblemType.VALIDATION,
                        ),
                    ),
                )
        }

        "string with spaces should fail" {
            UserName.of("user name") shouldBe
                Result.Failure(
                    listOf(
                        Problem(
                            "must contain only letters, numbers, underscores and periods",
                            Problem.ProblemType.VALIDATION,
                        ),
                    ),
                )
        }

        "valid name should succeed" {
            val result = UserName.of("Alice")
            result.shouldBeInstanceOf<Result.Success<UserName>>()
            result.data.value shouldBe "Alice"
        }

        "name with underscore should succeed" {
            val result = UserName.of("alice_smith")
            result.shouldBeInstanceOf<Result.Success<UserName>>()
            result.data.value shouldBe "alice_smith"
        }

        "name with period should succeed" {
            val result = UserName.of("alice.smith")
            result.shouldBeInstanceOf<Result.Success<UserName>>()
            result.data.value shouldBe "alice.smith"
        }

        "name with numbers should succeed" {
            val result = UserName.of("alice99")
            result.shouldBeInstanceOf<Result.Success<UserName>>()
            result.data.value shouldBe "alice99"
        }
    })
