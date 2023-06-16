package com.task2.data.remote

import com.task2.data.networkmodels.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipesApi {

    /**
     * Get all recipes by calling the hardcoded endpoint
     * https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json
     * @return RecipesResponse
     */
    @GET("android-test/recipes.json")
    suspend fun getRecipes(): Response<List<RecipesResponse>>
}
