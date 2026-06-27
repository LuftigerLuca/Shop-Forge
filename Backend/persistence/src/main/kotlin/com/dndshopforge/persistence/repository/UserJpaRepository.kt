package com.dndshopforge.persistence.repository

import com.dndshopforge.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, String> {
    fun findByName(name: String): UserEntity?

    fun existsByName(name: String): Boolean
}
