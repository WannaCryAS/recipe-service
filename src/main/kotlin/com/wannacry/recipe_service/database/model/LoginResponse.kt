package com.wannacry.recipe_service.database.model

data class LoginResponse(
    val token: String,
    val email: String,
    val name: String
)