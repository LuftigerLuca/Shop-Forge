package com.dndshopforge.domain.result

data class Problem(
    val message: String,
    val type: ProblemType? = null,
) {
    enum class ProblemType {
        VALIDATION,
        NOT_FOUND,
        CONFLICT,
        INTERNAL,
    }
}
