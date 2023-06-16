package com.task2.data.remote

import com.task2.data.networkmodels.RecipesResponse
import retrofit2.Response
import javax.inject.Inject

class RecipesDataSource @Inject constructor(
    private val recipesApi: RecipesApi
) : RecipesDataStore {

    override suspend fun fetchRecipes(): Result<List<RecipesResponse>> =
        getResponse {
            recipesApi.getRecipes()
        }

    companion object {
        suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
            return try {
                val result = request.invoke()
                result.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception(result.errorBody().toString()))
            } catch (e: Throwable) {
                e.printStackTrace()
                Result.failure(e)
            }
        }
    }
}
