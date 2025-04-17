package com.wannacry.recipe_service.controller

import com.wannacry.recipe_service.database.model.data.Meal
import com.wannacry.recipe_service.database.repository.MealRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/meals")
class MealController(private val mealService: MealRepository) {

    @PostMapping
    fun createMeal(@RequestBody meal: Meal): Meal {
        return mealService.save(meal)
    }

    @GetMapping("/{userId}")
    fun getMealsByUser(@PathVariable userId: String): List<Meal> {
        return mealService.findAllByUserId(userId)
    }

    @GetMapping("/meal/{id}")
    fun getMealById(@PathVariable id: String): Optional<Meal> {
        return mealService.findById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteMeal(@PathVariable id: String) {
        mealService.deleteById(id)
    }
}