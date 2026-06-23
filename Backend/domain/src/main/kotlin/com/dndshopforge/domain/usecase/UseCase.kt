package com.dndshopforge.domain.usecase

import com.dndshopforge.domain.result.Result

interface UseCase<RQ, RE> {
    fun execute(request: RQ): Result<RE>
}
