package com.dndshopforge.domain.user.response

import com.dndshopforge.domain.shared.model.Response
import com.dndshopforge.domain.user.model.UserId

data class LoginUserResponse(
    val id: UserId,
) : Response
