package com.task2.data.remote

import com.task2.data.networkmodels.RecipesResponse

interface RecipesDataStore {

    suspend fun fetchRecipes(): Result<List<RecipesResponse>>
}
