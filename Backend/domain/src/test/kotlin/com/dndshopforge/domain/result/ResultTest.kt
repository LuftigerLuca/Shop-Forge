package com.dndshopforge.domain.result

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class ResultTest :
    StringSpec({

        "map transforms success" {
            Result.Success(1).map { it * 2 } shouldBe Result.Success(2)
        }

        "map passes through failure" {
            val problems = listOf(Problem("error"))
            Result.Failure(problems).map<Int, Int> { it * 2 } shouldBe Result.Failure(problems)
        }

        "map with null returning transform" {
            Result.Success(1).map<Int, Int?> { null } shouldBe Result.Success(null)
        }

        "buildResult with multiple success binds" {
            val result =
                buildResult {
                    val a = Result.Success(1).bind()
                    val b = Result.Success(2).bind()
                    if (a == null || b == null) null else a + b
                }
            result shouldBe Result.Success(3)
        }

        "buildResult collects all failures" {
            val problemsA = listOf(Problem("error a"))
            val problemsB = listOf(Problem("error b"))
            val result =
                buildResult {
                    val a = Result.Failure(problemsA).bind()
                    val b = Result.Success("x").bind()
                    val c = Result.Failure(problemsB).bind()
                    if (a == null || b == null || c == null) {
                        null
                    } else {
                        "unused"
                    }
                }
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe problemsA + problemsB
        }

        "buildResult single bind returns success" {
            val result =
                buildResult<Int> {
                    Result.Success(42).bind()
                }
            result shouldBe Result.Success(42)
        }

        "buildResult with explicit null guard" {
            val result =
                buildResult {
                    val a = Result.Success(42).bind()
                    if (a == null) null else a
                }
            result shouldBe Result.Success(42)
        }

        "buildResult block returning null without errors throws NPE" {
            shouldThrow<NullPointerException> {
                buildResult<Int> { null }
            }
        }

        "ResultBuilder.hasErrors after all success binds" {
            val builder = ResultBuilder()
            with(builder) {
                Result.Success(1).bind()
                Result.Success(2).bind()
            }
            builder.hasErrors() shouldBe false
        }

        "ResultBuilder.hasErrors after a failure bind" {
            val builder = ResultBuilder()
            with(builder) {
                Result.Failure(listOf(Problem("error"))).bind()
            }
            builder.hasErrors() shouldBe true
        }

        "ResultBuilder.errors returns collected problems" {
            val builder = ResultBuilder()
            with(builder) {
                Result.Failure(listOf(Problem("a"))).bind()
                Result.Failure(listOf(Problem("b"))).bind()
            }
            builder.errors() shouldBe Result.Failure(listOf(Problem("a"), Problem("b")))
        }

        "Result.Failure with empty problems list" {
            val result: Result<Int> = Result.Failure(emptyList())
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe emptyList()
        }
    })
