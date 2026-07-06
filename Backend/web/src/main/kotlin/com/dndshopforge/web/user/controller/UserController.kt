package com.dndshopforge.web.user.controller

import com.dndshopforge.application.shared.factory.UseCaseFactory
import com.dndshopforge.domain.shared.result.ErrorCode
import com.dndshopforge.domain.shared.result.getOrNull
import com.dndshopforge.domain.user.usecase.LoginUserUseCase
import com.dndshopforge.web.user.mapper.LoginUserMapper
import com.dndshopforge.web.user.presenter.LoginUserPresenter
import com.dndshopforge.web.user.schema.request.LoginUserRequestSchema
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val factory: UseCaseFactory,
    private val presenter: LoginUserPresenter,
) {
    @PostMapping("/login")
    fun login(
        @RequestBody body: LoginUserRequestSchema,
    ): ResponseEntity<*> {
        val request =
            LoginUserMapper.toDomain(body).getOrNull()
                ?: return ResponseEntity.badRequest().body(
                    mapOf(
                        "errors" to
                            listOf(
                                mapOf("code" to ErrorCode.INVALID_INPUT.name, "message" to "Invalid input"),
                            ),
                    ),
                )

        return presenter.present(factory[LoginUserUseCase::class].execute(request))
    }
}
