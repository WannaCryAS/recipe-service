package com.wannacry.recipe_service.database.repository

import com.wannacry.recipe_service.database.model.data.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}