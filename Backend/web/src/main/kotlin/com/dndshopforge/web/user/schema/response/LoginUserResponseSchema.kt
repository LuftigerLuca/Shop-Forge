package com.dndshopforge.web.user.schema.response

data class LoginUserResponseSchema(
    val userId: String,
    val token: String,
)
