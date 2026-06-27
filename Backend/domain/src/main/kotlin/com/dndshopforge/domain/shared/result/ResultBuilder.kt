package com.dndshopforge.domain.shared.result

class ResultBuilder {
    private val problems = mutableListOf<Problem>()

    fun <T> Result<T>.bind(): T? =
        when (this) {
            is Result.Success -> {
                data
            }

            is Result.Failure -> {
                this@ResultBuilder.problems.addAll(this.problems)
                null
            }
        }

    fun hasErrors() = problems.isNotEmpty()

    fun errors() = Result.Failure(problems)
}

fun <T> buildResult(block: ResultBuilder.() -> T?): Result<T> {
    val builder = ResultBuilder()
    val value = builder.block()
    return if (builder.hasErrors()) {
        builder.errors()
    } else {
        Result.Success(value!!)
    }
}
