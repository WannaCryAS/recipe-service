package com.wannacry.recipe_service.security

import com.wannacry.recipe_service.database.model.LoginResponse
import com.wannacry.recipe_service.database.model.RegisterRequest
import com.wannacry.recipe_service.database.model.data.RefreshToken
import com.wannacry.recipe_service.database.model.RegisterResponse
import com.wannacry.recipe_service.database.model.data.TokenPair
import com.wannacry.recipe_service.database.model.data.User
import com.wannacry.recipe_service.database.repository.RefreshTokenRepository
import com.wannacry.recipe_service.database.repository.UserRepository
import com.wannacry.recipe_service.utils.General.HASH
import com.wannacry.recipe_service.exception.EmailAlreadyExistsException
import com.wannacry.recipe_service.exception.InvalidCredentialsException
import com.wannacry.recipe_service.utils.ApiCode.EMAIL_ALREADY_EXISTS
import com.wannacry.recipe_service.utils.ApiCode.INVALID_CREDENTIALS
import org.bson.types.ObjectId
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.security.MessageDigest
import java.time.Instant
import java.util.*

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val hashEncoder: HashEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun register(
       request: RegisterRequest
    ): RegisterResponse {

        if (userRepository.existsByEmail(request.email)) {
            throw EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS.description)
        }
        val hashedPassword = hashEncoder.encode(request.password)
        val user = User(
            name = request.name,
            email = request.email,
            hashPassword = hashedPassword
        )
        val savedUser = userRepository.save(user)

        return RegisterResponse(
            userId = savedUser.id.toHexString(),
            name = savedUser.name,
            email = savedUser.email
        )
    }

    fun login(email: String,
              password: String
    ): LoginResponse {
        val user = userRepository.findByEmail(email)
            ?: throw InvalidCredentialsException(INVALID_CREDENTIALS.description)

        if (!hashEncoder.matches(password, user.hashPassword)) {
            throw InvalidCredentialsException(INVALID_CREDENTIALS.description)
        }

        val newAccessToken = jwtService.generateAccessToken(user.id.toHexString())
        val newRefreshToken = jwtService.generateRefreshToken(user.id.toHexString())

        return LoginResponse(
            token = newAccessToken,
            email = user.email,
            name = user.name
        )
    }

    @Transactional
    fun refresh(refreshToken: String): TokenPair {
        if (!jwtService.validateRefreshToken(refreshToken)) {
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Invalid refresh token.")
        }

        val userId = jwtService.getUserIdFromToken(refreshToken)
        val user = userRepository.findById(ObjectId(userId)).orElseThrow {
            ResponseStatusException(HttpStatusCode.valueOf(401),"Invalid refresh token.")
        }

        val hashed = hashToken(refreshToken)
        refreshTokenRepository.findByUserIdAndHashedToken(user.id, hashed)
            ?: throw ResponseStatusException(
                HttpStatusCode.valueOf(401),
                "Refresh token not recognized (maybe used or expired?)")

        refreshTokenRepository.deleteByUserIdAndHashedToken(user.id, hashed)

        val newAccessToken = jwtService.generateAccessToken(userId)
        val newRefreshToken = jwtService.generateRefreshToken(userId)

        storeRefreshToken(user.id, newRefreshToken)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    private fun storeRefreshToken(userId: ObjectId, rawRefreshToken: String) {
        val hashed = hashToken(rawRefreshToken)
        val expiryMs = jwtService.refreshTokenValidityMs
        val expiresAt = Instant.now().plusMillis(expiryMs)

        refreshTokenRepository.save(
            RefreshToken(
                userId = userId,
                expiresAt = expiresAt,
                hashedToken = hashed

            )
        )

    }

    private fun hashToken( token: String): String {
        val digest = MessageDigest.getInstance(HASH)
        val hashBytes = digest.digest(token.encodeToByteArray())
        return Base64.getEncoder().encodeToString(hashBytes)

    }
}