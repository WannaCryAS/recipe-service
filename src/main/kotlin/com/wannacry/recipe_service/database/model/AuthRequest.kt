package com.wannacry.recipe_service.database.model

import com.wannacry.recipe_service.utils.General.REGEX
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class AuthRequest(
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$",
        message = "Password must be at least 8 characters long and contain at least one digit, uppercase and lowercase character."
    )
    val password: String
)

data class RegisterRequest(
    @field:NotBlank(message = "Name is required")
    val name: String,
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email format is invalid")
    val email: String,
//    @field:NotBlank(message = "Password is required")
//    @field:Size(min = 8, message = "Password must be at least 8 characters")
    @field:Pattern(
        regexp = REGEX,
        message = "Password must be at least 8 characters long and contain at least one digit, uppercase and lowercase character."
    )
    val password: String
)

data class RefreshRequest(
    val refreshToken: String
)
