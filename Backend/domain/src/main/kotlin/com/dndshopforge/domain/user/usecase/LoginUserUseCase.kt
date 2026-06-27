package com.dndshopforge.domain.user.usecase

import com.dndshopforge.domain.shared.model.Response
import com.dndshopforge.domain.shared.usecase.UseCase
import com.dndshopforge.domain.user.request.LoginUserRequest
import com.dndshopforge.domain.user.response.LoginUserResponse

interface LoginUserUseCase : UseCase<LoginUserRequest, LoginUserResponse>
