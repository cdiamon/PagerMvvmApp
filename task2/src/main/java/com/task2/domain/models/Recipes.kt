package com.task2.domain.models

sealed interface Recipes {

    data class Data(
        val itemsList: List<Item>,
    ) : Recipes {
        sealed interface Item {
            data class RecipeItem(
                val image: String,
                val name: String,
                val headline: String,
            ) : Item

            data class DateItem(
                val date: String,
            ) : Item
        }
    }

    object Loading : Recipes

    data class Error(
        val message: String,
    ) : Recipes
}
