package com.task2.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.task2.R
import com.task2.databinding.DialogRecipeDetailsBinding
import com.task2.domain.models.Recipes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : BottomSheetDialogFragment() {

    lateinit var binding: DialogRecipeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogRecipeDetailsBinding.inflate(inflater, container, false)
        val recipe =
            arguments?.getSerializable("com.task2.domain.models.Recipes.Data.Item.RecipeItem")
                    as? Recipes.Data.Item.RecipeItem

        recipe?.let {
            binding.recipeTitle.text = it.name
            binding.recipeDescription.text = it.headline
            Glide
                .with(requireContext())
                .load(it.image)
                .centerCrop()
                .into(binding.recipeImage)
        } ?: run {
            Toast.makeText(requireContext(), getText(R.string.generic_error), Toast.LENGTH_SHORT)
                .show()
            findNavController().navigateUp()
        }

        return binding.root
    }
}
