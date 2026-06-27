package com.dndshopforge.domain.user.request

import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.shared.result.buildResult
import com.dndshopforge.domain.user.model.UserName
import com.dndshopforge.domain.user.model.UserPassword

class LoginUserRequest private constructor(
    val username: UserName,
    val password: UserPassword,
) {
    companion object {
        fun of(
            username: String,
            password: String,
        ): Result<LoginUserRequest> =
            buildResult {
                val username = UserName.of(username).bind()
                val password = UserPassword.of(password).bind()

                if (username == null || password == null) {
                    null
                } else {
                    LoginUserRequest(username, password)
                }
            }
    }
}
