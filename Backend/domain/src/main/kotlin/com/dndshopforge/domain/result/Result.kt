package com.dndshopforge.domain.result

sealed class Result<out T> {
    data class Success<T>(
        val data: T,
    ) : Result<T>()

    data class Failure(
        val problems: List<Problem>,
    ) : Result<Nothing>()
}

fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> =
    when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Failure -> this
    }

fun <T, R> Result<T>.zip(other: Result<R>): Result<Pair<T, R>> {
    val leftProblems = (this as? Result.Failure)?.problems ?: emptyList()
    val rightProblems = (other as? Result.Failure)?.problems ?: emptyList()
    val allProblems = leftProblems + rightProblems

    return if (allProblems.isEmpty()) {
        val leftData = (this as Result.Success).data
        val rightData = (other as Result.Success).data
        Result.Success(leftData to rightData)
    } else {
        Result.Failure(allProblems)
    }
}
