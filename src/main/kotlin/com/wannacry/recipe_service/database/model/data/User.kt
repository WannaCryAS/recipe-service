package com.wannacry.recipe_service.database.model.data

import com.wannacry.recipe_service.utils.Documents.USER
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(USER)
data class User(
    @Id val id: ObjectId = ObjectId(),
    val email: String,
    val hashPassword: String
)