package com.task1.models

data class MenuModel(
    val subscription: Subscription,
    val recipes: List<Recipe>,
    val selectedRecipesIds: ArrayList<Int>,
)
