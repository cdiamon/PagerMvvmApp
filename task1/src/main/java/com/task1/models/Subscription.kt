package com.task1.models

data class Subscription(
    val id: Int,
    val deliveryDay: String,
    val isForFamily: Boolean,
) {

    fun getMaxRecipes(): Int {
        return if (isForFamily) MAX_SELECTED_RECIPES_FAMILY else MAX_SELECTED_RECIPES
    }

    private companion object {
        const val MAX_SELECTED_RECIPES = 3
        const val MAX_SELECTED_RECIPES_FAMILY = 5
    }
}
