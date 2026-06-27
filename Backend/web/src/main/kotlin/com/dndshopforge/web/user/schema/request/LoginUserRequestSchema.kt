package com.dndshopforge.web.user.schema.request

data class LoginUserRequestSchema(
    val username: String,
    val password: String,
)
