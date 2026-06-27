package com.dndshopforge.web.user.controller

import com.dndshopforge.domain.shared.result.getOrNull
import com.dndshopforge.web.user.mapper.LoginUserMapper
import com.dndshopforge.web.user.schema.request.LoginUserRequestSchema
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user")
class UserController {
    @PostMapping("/login")
    fun login(
        @RequestBody body: LoginUserRequestSchema,
    ): ResponseEntity<Map<String, String>> {
        val request = LoginUserMapper.toDomain(body).getOrNull()
            ?: return ResponseEntity.badRequest().body(mapOf("message" to "Invalid input"))

        return ResponseEntity.ok(mapOf("message" to "Not implemented"))
    }
}
