package com.dndshopforge.domain.user.gateway

import com.dndshopforge.domain.shared.gateway.ReadWriteGateway
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.user.model.User
import com.dndshopforge.domain.user.model.UserId

interface UserGateway : ReadWriteGateway<User, UserId> {
    fun findByUsername(username: String): Result<User>

    fun existsByUsername(username: String): Boolean
}
