package com.dndshopforge.domain.gateway

import com.dndshopforge.domain.model.user.User
import com.dndshopforge.domain.model.user.UserId
import com.dndshopforge.domain.result.Result

interface UserGateway : ReadWriteGateway<User, UserId> {
    fun findByUsername(username: String): Result<User>
    fun existsByUsername(username: String): Boolean
}
