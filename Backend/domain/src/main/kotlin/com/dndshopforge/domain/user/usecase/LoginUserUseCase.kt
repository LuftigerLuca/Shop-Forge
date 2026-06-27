package com.dndshopforge.domain.user.usecase

import com.dndshopforge.domain.shared.model.Response
import com.dndshopforge.domain.shared.usecase.UseCase
import com.dndshopforge.domain.user.request.LoginUserRequest

interface LoginUserUseCase : UseCase<LoginUserRequest, Response.Empty>
