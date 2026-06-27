package com.dndshopforge.domain.user.model

import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.util.UUID

class UserTest :
    StringSpec({

        "without id should generate random id" {
            val result = User.of(name = "Alice", passwordHash = "a".repeat(23))
            result.shouldBeInstanceOf<Result.Success<User>>()
            result.data.name.value shouldBe "Alice"
            result.data.passwordHash.value shouldBe "a".repeat(23)
            UUID.fromString(result.data.id.value)
        }

        "with valid id, name and passwordHash should succeed" {
            val id = UUID.randomUUID().toString()
            val result = User.of(id = id, name = "Bob", passwordHash = "b".repeat(23))
            result.shouldBeInstanceOf<Result.Success<User>>()
            result.data.id.value shouldBe id
            result.data.name.value shouldBe "Bob"
            result.data.passwordHash.value shouldBe "b".repeat(23)
        }

        "with blank name should fail" {
            val result = User.of(name = "", passwordHash = "a".repeat(23))
            result shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "with blank passwordHash should fail" {
            val result = User.of(name = "Alice", passwordHash = "")
            result shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "with blank id and blank name should accumulate both problems" {
            val result = User.of(id = "", name = "", passwordHash = "a".repeat(23))
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe
                listOf(
                    Problem("must not be blank", Problem.ProblemType.VALIDATION),
                    Problem("must not be blank", Problem.ProblemType.VALIDATION),
                )
        }

        "with all invalid inputs should accumulate all three problems" {
            val result = User.of(id = "", name = "", passwordHash = "")
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe
                listOf(
                    Problem("must not be blank", Problem.ProblemType.VALIDATION),
                    Problem("must not be blank", Problem.ProblemType.VALIDATION),
                    Problem("must not be blank", Problem.ProblemType.VALIDATION),
                )
        }
    })
