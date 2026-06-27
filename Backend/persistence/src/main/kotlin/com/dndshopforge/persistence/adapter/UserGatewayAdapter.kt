package com.dndshopforge.persistence.adapter

import com.dndshopforge.domain.shared.result.Problem
import com.dndshopforge.domain.shared.result.Result
import com.dndshopforge.domain.user.gateway.UserGateway
import com.dndshopforge.domain.user.model.User
import com.dndshopforge.domain.user.model.UserId
import com.dndshopforge.domain.user.model.UserName
import com.dndshopforge.persistence.entity.UserEntity
import com.dndshopforge.persistence.mapper.UserMapper
import com.dndshopforge.persistence.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserGatewayAdapter(
    private val repository: UserRepository,
) : UserGateway {
    private fun toResult(entity: UserEntity): Result<User> {
        val mapped = UserMapper.toDomain(entity)
        return when (mapped) {
            is Result.Success -> {
                mapped
            }

            is Result.Failure -> {
                Result.Failure(
                    mapped.problems.map {
                        Problem(
                            message = it.message,
                            type = Problem.ProblemType.PERSISTENCE,
                        )
                    },
                )
            }
        }
    }

    override fun findById(id: UserId): Result<User> =
        repository
            .findById(id.value)
            .map { toResult(it) }
            .orElse(Result.Failure(listOf(Problem("User not found", Problem.ProblemType.NOT_FOUND))))

    override fun existsById(id: UserId): Boolean = repository.existsById(id.value)

    override fun count(): Long = repository.count()

    override fun save(entity: User): Result<User> {
        repository.save(UserMapper.toEntity(entity))
        return Result.Success(entity)
    }

    override fun deleteById(id: UserId) {
        repository.deleteById(id.value)
    }

    override fun findByUsername(username: UserName): Result<User> =
        repository
            .findByName(username.value)
            ?.let { toResult(it) }
            ?: Result.Failure(listOf(Problem("User not found", Problem.ProblemType.NOT_FOUND)))

    override fun existsByUsername(username: UserName): Boolean = repository.existsByName(username.value)
}
