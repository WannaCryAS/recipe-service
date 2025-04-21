package com.wannacry.recipe_service.database.model

data class RegisterResponse(
    val userId: String,
    val email: String,
    val name: String
)
