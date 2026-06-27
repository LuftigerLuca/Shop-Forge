package com.dndshopforge.domain.shared.usecase

import com.dndshopforge.domain.shared.model.Response
import com.dndshopforge.domain.shared.result.Result

interface UseCase<RQ, RE : Response> {
    fun execute(request: RQ): Result<RE>
}
