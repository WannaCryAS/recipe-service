package com.wannacry.recipe_service.database.model

data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T? = null
)
