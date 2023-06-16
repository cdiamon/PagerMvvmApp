package com.task1

import com.task1.models.MenuModel
import com.task1.models.Recipe

class MenuRepository constructor(private val menuModel: MenuModel) {

    // mark a single recipe as selected with respect to the maximum number of selected recipes
    fun selectRecipe(recipeId: Int): Result<Unit> =
        when {
            menuModel.selectedRecipesIds.contains(recipeId) ->
                Result.failure(Exception("Recipe already selected"))
            menuModel.selectedRecipesIds.size >= menuModel.subscription.getMaxRecipes() ->
                Result.failure(Exception("Maximum number of selected recipes reached"))
            menuModel.recipes.find { it.id == recipeId } == null ->
                Result.failure(Exception("Recipe not found"))
            else -> {
                menuModel.selectedRecipesIds.add(recipeId)
                Result.success(Unit)
            }
        }

    // mark multiple recipes as selected
    fun selectRecipes(recipeIds: List<Int>): Result<Unit> {
        var result = Result.success(Unit)
        recipeIds.forEach {
            val r = selectRecipe(it)
            if (r.isFailure) {
                result = r
                return@forEach
            }
        }
        return result
    }

    // unselect a single selected recipe
    fun unselectRecipe(recipeId: Int): Result<Unit> =
        when {
            menuModel.recipes.find { it.id == recipeId } == null ->
                Result.failure(Exception("Recipe not found"))
            menuModel.selectedRecipesIds.isEmpty() ->
                Result.failure(Exception("No recipes selected"))
            menuModel.selectedRecipesIds.find { it == recipeId } == null ->
                Result.failure(Exception("No such recipe selected"))
            else -> {
                menuModel.selectedRecipesIds.remove(recipeId)
                Result.success(Unit)
            }
        }

    // unselect multiple selected recipes
    fun unselectRecipes(recipeIds: List<Int>): Result<Unit> {
        var result = Result.success(Unit)
        recipeIds.forEach {
            val r = unselectRecipe(it)
            if (r.isFailure) {
                result = r
                return@forEach
            }
        }
        return result
    }

    // how many recipes have been selected
    fun selectedRecipesCount(): Int {
        return menuModel.selectedRecipesIds.size
    }

    // request a list of selected recipes
    fun selectedRecipes(): Result<List<Recipe>> =
        if (menuModel.selectedRecipesIds.isNotEmpty()) {
            val selectedRecipes = menuModel.recipes.filter { it.id in menuModel.selectedRecipesIds }
            Result.success(selectedRecipes)
        } else {
            Result.failure(Exception("No recipes selected"))
        }

    // request a list of recipes which have a certain tag
    fun recipesWithTag(tag: String): Result<List<Recipe>> {
        val recipesWithTag = menuModel.recipes.filter { it.tags.contains(tag) }
        return if (recipesWithTag.isNotEmpty()) {
            Result.success(recipesWithTag)
        } else {
            Result.failure(Exception("No recipes with tag $tag"))
        }
    }
}
