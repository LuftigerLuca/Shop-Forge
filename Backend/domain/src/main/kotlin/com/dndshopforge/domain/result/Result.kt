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

fun <T, R> Result<T>.zip(other: Result<R>): Result<Pair<T, R>> =
    when (this) {
        is Result.Success -> {
            when (other) {
                is Result.Success -> Result.Success(data to other.data)
                is Result.Failure -> other
            }
        }

        is Result.Failure -> {
            when (other) {
                is Result.Success -> this
                is Result.Failure -> Result.Failure(problems + other.problems)
            }
        }
    }
