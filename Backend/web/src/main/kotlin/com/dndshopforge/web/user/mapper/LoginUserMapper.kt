package com.dndshopforge.web.user.mapper

import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.user.request.LoginUserRequest
import com.dndshopforge.domain.user.response.LoginUserResponse
import com.dndshopforge.web.user.schema.request.LoginUserRequestSchema
import com.dndshopforge.web.user.schema.response.LoginUserResponseSchema

object LoginUserMapper {
    fun toDomain(schema: LoginUserRequestSchema): Result<LoginUserRequest> =
        LoginUserRequest.of(
            username = schema.username,
            password = schema.password,
        )

    fun toSchema(
        domain: LoginUserResponse,
        token: String,
    ): LoginUserResponseSchema =
        LoginUserResponseSchema(
            userId = domain.id.value,
            token = token,
        )
}
