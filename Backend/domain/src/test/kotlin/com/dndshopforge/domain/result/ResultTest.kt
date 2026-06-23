package com.dndshopforge.domain.result

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

        "zip two successes" {
            val a: Result<Int> = Result.Success(1)
            val b: Result<String> = Result.Success("x")
            a.zip(b) shouldBe Result.Success(1 to "x")
        }

        "zip success with failure" {
            val problems = listOf(Problem("error"))
            val a: Result<Int> = Result.Success(1)
            val b: Result<String> = Result.Failure(problems)
            a.zip(b) shouldBe Result.Failure(problems)
        }

        "zip failure with success" {
            val problems = listOf(Problem("error"))
            val a: Result<Int> = Result.Failure(problems)
            val b: Result<String> = Result.Success("x")
            a.zip(b) shouldBe Result.Failure(problems)
        }

        "zip two failures accumulates problems" {
            val problemsA = listOf(Problem("error a"))
            val problemsB = listOf(Problem("error b"))
            val a: Result<Int> = Result.Failure(problemsA)
            val b: Result<String> = Result.Failure(problemsB)
            val result = a.zip(b)
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe problemsA + problemsB
        }
    })
