package com.dndshopforge.persistence.mapper

import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.user.model.User
import com.dndshopforge.persistence.entity.UserEntity

object UserMapper {
    fun toEntity(domain: User): UserEntity =
        UserEntity(
            id = domain.id.value,
            name = domain.name.value,
            passwordHash = domain.passwordHash.value,
        )

    fun toDomain(entity: UserEntity): Result<User> =
        User.of(
            id = entity.id,
            name = entity.name,
            passwordHash = entity.passwordHash,
        )
}
