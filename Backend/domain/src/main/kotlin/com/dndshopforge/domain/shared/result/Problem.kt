package com.dndshopforge.domain.shared.result

data class Problem(
    val message: String,
    val type: ProblemType? = null,
) {
    enum class ProblemType {
        VALIDATION,
        NOT_FOUND,
        UNAUTHORIZED,
        CONFLICT,
        INTERNAL,
        PERSISTENCE,
    }
}
