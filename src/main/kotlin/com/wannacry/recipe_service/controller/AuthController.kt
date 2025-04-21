package com.wannacry.recipe_service.controller

import com.wannacry.recipe_service.database.model.AuthRequest
import com.wannacry.recipe_service.database.model.LoginResponse
import com.wannacry.recipe_service.database.model.RefreshRequest
import com.wannacry.recipe_service.database.model.RegisterRequest
import com.wannacry.recipe_service.database.model.ApiResponse
import com.wannacry.recipe_service.database.model.RegisterResponse
import com.wannacry.recipe_service.database.model.data.TokenPair
import com.wannacry.recipe_service.security.AuthService
import com.wannacry.recipe_service.utils.ApiCode
import com.wannacry.recipe_service.utils.Route.AUTH
import com.wannacry.recipe_service.utils.Route.LOGIN
import com.wannacry.recipe_service.utils.Route.REFRESH
import com.wannacry.recipe_service.utils.Route.REGISTER
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity

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
        @Valid @RequestBody body: RegisterRequest
    ): ResponseEntity<ApiResponse<RegisterResponse>> {
        val response = authService.register(body)

        return ResponseEntity.ok(
            ApiResponse(
                code = ApiCode.USER_REGISTERED.responseCode,
                message = ApiCode.USER_REGISTERED.description,
                data = response

            )
        )
    }

    @PostMapping(LOGIN)
    fun login(
        @RequestBody body: AuthRequest
    ): ResponseEntity<ApiResponse<LoginResponse>> {
        val response = authService.login(body.email, body.password)
        return ResponseEntity.ok(
            ApiResponse(
                code = ApiCode.USER_LOGGED_IN.responseCode,
                message = ApiCode.USER_LOGGED_IN.description,
                data = response

            )
        )
    }

    @PostMapping(REFRESH)
    fun refresh(
        @RequestBody body: RefreshRequest
    ): TokenPair {
        return authService.refresh(body.refreshToken)
    }
}