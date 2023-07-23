package com.task2.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task2.domain.models.Recipes
import com.task2.domain.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    private val _recipesData: MutableLiveData<Recipes> = MutableLiveData()
    val recipesData: LiveData<Recipes> = _recipesData

    init {
        loadRecipes()
    }

    private fun loadRecipes() = viewModelScope.launch {
        _recipesData.value = Recipes.Loading
        delay(1000) // to show loading
        repository.loadRecipes().collect {
            _recipesData.value = it
        }
    }
}
