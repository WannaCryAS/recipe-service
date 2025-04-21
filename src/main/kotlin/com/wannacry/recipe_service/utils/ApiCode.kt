package com.wannacry.recipe_service.utils

enum class ApiCode(val responseCode: String, val description: String) {
    // Success Codes
    GENERAL_SUCCESS("000000", "Successful"),
    //AUTH
    USER_REGISTERED("ARS-000000", "User registered successfully"),
    USER_LOGGED_IN("ALS-000000", "Login successful"),

    // Error Codes
    SERVER_ERROR("SE-501000", "Internal server error"),
    VALIDATION_ERROR("VE-999999", " Validation Error"),
    INVALID_CREDENTIALS("IC-402000", "Invalid email or password"),
    USER_NOT_FOUND("UNF-401000", "User not found"),
    EMAIL_ALREADY_EXISTS("EAE-409000", "Email already exists")
}