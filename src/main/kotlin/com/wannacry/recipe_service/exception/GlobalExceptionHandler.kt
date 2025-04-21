package com.wannacry.recipe_service.exception

import com.wannacry.recipe_service.database.model.ApiResponse
import com.wannacry.recipe_service.utils.ApiCode.EMAIL_ALREADY_EXISTS
import com.wannacry.recipe_service.utils.ApiCode.INVALID_CREDENTIALS
import com.wannacry.recipe_service.utils.ApiCode.VALIDATION_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentials(e: InvalidCredentialsException): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity.badRequest().body(
            ApiResponse(
                code = INVALID_CREDENTIALS.responseCode,
                message = e.message ?: INVALID_CREDENTIALS.description,
                data = null
            )
        )
    }

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleEmailAlreadyExists(e: EmailAlreadyExistsException): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity.status(409).body(
            ApiResponse(
                code = EMAIL_ALREADY_EXISTS.responseCode,
                message = e.message ?: EMAIL_ALREADY_EXISTS.description,
                data = null
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        val errorMessage = e.bindingResult.fieldErrors
            .joinToString(", ") { "${it.defaultMessage}" }

        return ResponseEntity.badRequest().body(
            ApiResponse(
                code = VALIDATION_ERROR.responseCode,
                message = errorMessage,
                data = null
            )
        )
    }

}