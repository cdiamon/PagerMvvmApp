package com.task2.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.task2.databinding.ActivityRecipesBinding
import com.task2.domain.models.Recipes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesActivity : AppCompatActivity() {

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var binding: ActivityRecipesBinding
    private val recipesAdapter: RecipesAdapter by lazy { RecipesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()
    }

    private fun fetchData() {
        viewModel.loadRecipes()
        viewModel.recipesData.observe(this) { result ->
            result.onFailure {
                showErrorMessage(it.message ?: "Error")
            }.onSuccess {
                when (it) {
                    is Recipes.Loading -> showLoader()
                    is Recipes.Data -> showData(it.itemsList)
                    is Recipes.Error -> showErrorMessage(it.message)
                }
            }
        }
    }

    private fun showData(itemsList: List<Recipes.Data.Item>) {
        binding.progressIndicator.isVisible = false
        binding.recipesRecyclerView.isVisible = true
        binding.recipesRecyclerView.adapter = recipesAdapter
        recipesAdapter.submitList(itemsList)
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showLoader() {
        binding.progressIndicator.isVisible = true
        binding.recipesRecyclerView.isVisible = false
        binding.progressIndicator.show()
    }
}
