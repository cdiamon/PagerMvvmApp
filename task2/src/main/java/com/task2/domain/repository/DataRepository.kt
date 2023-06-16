package com.task2.domain.repository

import com.task2.data.remote.RecipesDataStore
import com.task2.domain.mappers.RecipesMapper
import com.task2.domain.models.Recipes
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
interface DataRepository {
    fun loadRecipes(): Flow<Recipes>
}

class DataRepositoryImpl @Inject constructor(
    private val dataSource: RecipesDataStore,
    private val recipesMapper: RecipesMapper,
) : DataRepository {

    override fun loadRecipes(): Flow<Recipes> =
        flow {
            val response = dataSource.fetchRecipes()
            response
                .onFailure {
                    emit(Recipes.Error(it.message ?: "generic error"))
                }
                .onSuccess {
                    emit(recipesMapper.map(it))
                }
        }
}
