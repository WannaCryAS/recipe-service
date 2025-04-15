package com.wannacry.recipe_service.database.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class AuthRequest(
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$",
        message = "Password must be at least 8 characters long and contain at least one digit, uppercase and lowercase character."
    )
    val password: String
)

data class RefreshRequest(
    val refreshToken: String
)
