package com.task2.domain.mappers

import com.task2.data.networkmodels.RecipesResponse
import com.task2.domain.models.Recipes
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.Locale
import javax.inject.Inject

class RecipesMapper @Inject constructor() {
    fun map(response: Result<List<RecipesResponse>>): Result<Recipes> {
        return response.mapCatching { recipesList ->
            val itemsList = mutableListOf<Recipes.Data.Item>()
            itemsList.add(Recipes.Data.Item.DateItem(getCurrentDate()))
            recipesList.forEach { from ->
                itemsList.add(
                    Recipes.Data.Item.RecipeItem(
                        image = from.image,
                        name = from.name,
                        headline = from.headline,
                    )
                )
            }
            Recipes.Data(
                itemsList = itemsList
            )
        }
    }

    private fun getCurrentDate(): String {
        val currentDateTime =
            kotlinx.datetime.Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        // formatted to "dd MMM" as 11 Jun

        return "${currentDateTime.dayOfMonth} ${
            currentDateTime.month.name.substring(0, 3).lowercase()
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
        }"
    }
}
