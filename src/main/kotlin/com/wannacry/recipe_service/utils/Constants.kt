package com.wannacry.recipe_service.utils

object General {
    const val EXPIRE_AFTER = "0s"
    const val HASH = "SHA-256"
    const val REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d).{8,}\\\$"
}

object Auth {
    const val ACCESS = "access"
    const val REFRESH = "refresh"
    const val TYPE = "type"
    const val PREFIX_BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"
}

object Documents {
    const val USER = "users"
    const val REFRESH_TOKEN = "refresh_token"
}

object Route {
    const val AUTH = "/auth"
    const val REGISTER = "/register"
    const val LOGIN = "/login"
    const val REFRESH = "/refresh"

}