package com.dndshopforge.domain.model.user

import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.buildResult

data class User private constructor(
    val id: UserId,
    val name: UserName,
    val passwordHash: UserPasswordHash,
) {
    companion object {
        fun of(
            id: String? = null,
            name: String,
            passwordHash: String,
        ): Result<User> {
            val idResult = id?.let { UserId.of(it) } ?: UserId.random()

            return buildResult {
                val userId = idResult.bind()
                val userName = UserName.of(name).bind()
                val userHash = UserPasswordHash.of(passwordHash).bind()

                if (userId == null || userName == null || userHash == null) {
                    null
                } else {
                    User(userId, userName, userHash)
                }
            }
        }
    }
}
