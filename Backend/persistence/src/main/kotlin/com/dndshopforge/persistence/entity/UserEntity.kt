package com.dndshopforge.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @Column(length = 36, nullable = false)
    val id: String,
    @Column(length = 36, nullable = false, unique = true)
    val name: String,
    @Column(name = "password_hash", length = 128, nullable = false)
    val passwordHash: String,
)
