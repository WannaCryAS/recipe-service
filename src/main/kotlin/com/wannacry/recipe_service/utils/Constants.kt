package com.wannacry.recipe_service.utils

object General {
    const val EXPIRE_AFTER = "0s"
}

object Auth {
    const val ACCESS = "access"
    const val REFRESH = "refresh"
    const val TYPE = "type"
    const val PREFIX_BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"
    const val REGISTER = "register"
    const val LOGIN = "login"
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