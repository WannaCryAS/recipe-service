package com.wannacry.recipe_service.controller

import com.wannacry.recipe_service.database.model.AuthRequest
import com.wannacry.recipe_service.database.model.RefreshRequest
import com.wannacry.recipe_service.database.model.data.TokenPair
import com.wannacry.recipe_service.security.AuthService
import com.wannacry.recipe_service.utils.Auth.LOGIN
import com.wannacry.recipe_service.utils.Auth.REFRESH
import com.wannacry.recipe_service.utils.Auth.REGISTER
import jakarta.validation.Valid
import org.apache.naming.ResourceRef.AUTH
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(AUTH)
class AuthController(
    private val authService: AuthService
) {

    @PostMapping(REGISTER)
    fun register(
        @Valid @RequestBody body: AuthRequest
    ) {
        authService.register(body.email, body.password)
    }

    @PostMapping(LOGIN)
    fun login(
        @RequestBody body: AuthRequest
    ): TokenPair {
        return authService.login(body.email, body.password)
    }

    @PostMapping(REFRESH)
    fun refresh(
        @RequestBody body: RefreshRequest
    ): TokenPair {
        return authService.refresh(body.refreshToken)
    }
}