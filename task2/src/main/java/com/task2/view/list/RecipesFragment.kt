package com.task2.view.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.task2.databinding.FragmentRecipesBinding
import com.task2.domain.models.Recipes
import com.task2.domain.models.Recipes.Data.Item.RecipeItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    lateinit var binding: FragmentRecipesBinding
    private val viewModel: RecipesViewModel by viewModels()
    private val listener: (RecipeItem) -> Unit = { navigateToDetails(it) }
    private val recipesAdapter: RecipesAdapter by lazy { RecipesAdapter(listener) }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        observeUI()
        return binding.root
    }

    private fun observeUI() {
        viewModel.recipesData.observe(this.viewLifecycleOwner) { result ->
            when (result) {
                is Recipes.Loading -> showLoader()
                is Recipes.Data -> showData(result.itemsList)
                is Recipes.Error -> showErrorMessage(result.message)
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

    private fun navigateToDetails(recipe: RecipeItem) {
        findNavController().navigate(
            RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment2()
                .setComTask2DomainModelsRecipesDataItemRecipeItem(recipe)
        )
    }
}
