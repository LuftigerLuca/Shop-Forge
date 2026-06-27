package com.dndshopforge.application.shared.security

interface PasswordHasher {
    fun hash(password: String): String

    fun verify(
        password: String,
        hash: String,
    ): Boolean
}
