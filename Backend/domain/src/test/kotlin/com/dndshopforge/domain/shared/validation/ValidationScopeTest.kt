package com.dndshopforge.domain.shared.validation

import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.validation.ValidationScope
import com.dndshopforge.domain.shared.validation.validate
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class ValidationScopeTest :
    StringSpec({

        "validate with no otherwise calls returns success" {
            val result = validate("valid") {}
            result shouldBe Result.Success("valid")
        }

        "validate with all passing conditions returns success" {
            val result =
                validate("hello") {
                    (value.isNotBlank()) otherwise Problem("blank")
                    (value.length >= 2) otherwise Problem("too short")
                }
            result shouldBe Result.Success("hello")
        }

        "validate with one failing condition returns failure with that problem" {
            val result =
                validate("") {
                    (value.isNotBlank()) otherwise Problem("must not be blank")
                }
            result shouldBe
                Result.Failure(listOf(Problem("must not be blank")))
        }

        "validate with multiple failing conditions returns all problems" {
            val result =
                validate("") {
                    (value.isNotBlank()) otherwise Problem("must not be blank")
                    (value.length >= 3) otherwise Problem("too short")
                }
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe
                listOf(
                    Problem("must not be blank"),
                    Problem("too short"),
                )
        }

        "validate interleaves passing and failing conditions" {
            val result =
                validate("ab") {
                    (value.isNotBlank()) otherwise Problem("blank")
                    (value.length >= 3) otherwise Problem("too short")
                    (value.length <= 10) otherwise Problem("too long")
                }
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe listOf(Problem("too short"))
        }

        "validate works with non-string types" {
            val result =
                validate(42) {
                    (value > 0) otherwise Problem("must be positive")
                    (value < 100) otherwise Problem("must be below 100")
                }
            result shouldBe Result.Success(42)
        }

        "validate with non-string type and failing condition" {
            val result =
                validate(-1) {
                    (value > 0) otherwise Problem("must be positive")
                }
            result shouldBe
                Result.Failure(listOf(Problem("must be positive")))
        }

        "otherwise does not add problem when condition is true" {
            val scope = ValidationScope("hello")
            with(scope) {
                true otherwise Problem("should not appear")
            }
            scope.problems shouldBe emptyList()
        }

        "otherwise adds problem when condition is false" {
            val scope = ValidationScope("")
            with(scope) {
                false otherwise Problem("should appear")
            }
            scope.problems shouldBe listOf(Problem("should appear"))
        }

        "otherwise with ProblemType" {
            val result =
                validate("x") {
                    (false) otherwise
                        Problem("validation error", Problem.ProblemType.VALIDATION)
                }
            result shouldBe
                Result.Failure(
                    listOf(Problem("validation error", Problem.ProblemType.VALIDATION)),
                )
        }
    })
