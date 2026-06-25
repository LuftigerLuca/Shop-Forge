package com.dndshopforge.domain.validation

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result

class ValidationScope<T>(
    val value: T,
) {
    private val _problems = mutableListOf<Problem>()
    val problems: List<Problem> get() = _problems

    infix fun Boolean.otherwise(problem: Problem) {
        if (!this) _problems.add(problem)
    }
}

fun <T> validate(
    value: T,
    block: ValidationScope<T>.() -> Unit,
): Result<T> {
    val scope = ValidationScope(value)
    scope.block()
    return if (scope.problems.isEmpty()) {
        Result.Success(value)
    } else {
        Result.Failure(scope.problems)
    }
}
