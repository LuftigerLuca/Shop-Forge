package com.dndshopforge.domain.shared.result

sealed class Result<out T> {
    data class Success<T>(
        val data: T,
    ) : Result<T>()

    data class Failure(
        val problems: List<Problem>,
    ) : Result<Nothing>()
}

fun <T> Result<T>.getOrNull(): T? =
    when (this) {
        is Result.Success -> data
        is Result.Failure -> null
    }

fun <T> Result<T>.isSuccess() = this is Result.Success

fun <T> Result<T>.isFailure() = this is Result.Failure

fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> =
    when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Failure -> this
    }
