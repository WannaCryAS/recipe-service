package com.wannacry.recipe_service.database.repository

import com.wannacry.recipe_service.database.model.data.Meal
import org.springframework.data.mongodb.repository.MongoRepository

interface MealRepository : MongoRepository<Meal, String> {
    fun findAllByUserId(userId: String): List<Meal>
}