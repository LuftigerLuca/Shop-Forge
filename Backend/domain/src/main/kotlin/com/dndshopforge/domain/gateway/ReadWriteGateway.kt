package com.dndshopforge.domain.gateway

import com.dndshopforge.domain.result.Result

interface ReadWriteGateway<T, ID> {
    fun findById(id: ID): Result<T>

    fun existsById(id: ID): Boolean

    fun count(): Long

    fun save(entity: T): Result<T>

    fun deleteById(id: ID)
}
