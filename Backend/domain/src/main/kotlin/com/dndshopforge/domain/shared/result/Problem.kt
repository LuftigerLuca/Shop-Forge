package com.dndshopforge.domain.shared.result

data class Problem(
    val code: ErrorCode = ErrorCode.INTERNAL_ERROR,
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
