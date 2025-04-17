package com.wannacry.recipe_service.database.model.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("meal")
data class Meal(
    @Id val id: String,
    val title: String,
    val userId: String,
    val item: List<Item> = emptyList(),
    val steps: List<Step> = emptyList()
)

data class Item(
    val name: String,
    val measure: Double,
    val unit: String
)

data class Step(
    val stepOrder: Int,
    val stepDescription: String
)

