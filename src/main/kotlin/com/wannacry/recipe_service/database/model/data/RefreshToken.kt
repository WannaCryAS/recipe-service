package com.wannacry.recipe_service.database.model.data

import com.wannacry.recipe_service.utils.Documents.REFRESH_TOKEN
import com.wannacry.recipe_service.utils.General.EXPIRE_AFTER
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(REFRESH_TOKEN)
data class RefreshToken(
    val userId: ObjectId,
    @Indexed(expireAfter = EXPIRE_AFTER)
    val expiresAt: Instant,
    val hashedToken: String,
    val createdAt: Instant = Instant.now()
)
