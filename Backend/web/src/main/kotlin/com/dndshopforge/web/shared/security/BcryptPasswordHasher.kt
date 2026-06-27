package com.dndshopforge.web.shared.security

import com.dndshopforge.application.shared.security.PasswordHasher
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class BcryptPasswordHasher : PasswordHasher {
    override fun hash(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())

    override fun verify(
        password: String,
        hash: String,
    ): Boolean = BCrypt.checkpw(password, hash)
}
