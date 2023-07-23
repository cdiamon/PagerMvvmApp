package com.task2.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task2.databinding.ItemDateBinding
import com.task2.databinding.ItemRecipeBinding
import com.task2.domain.models.Recipes.Data.Item

class RecipesAdapter(private val listener: (Item.RecipeItem) -> Unit) :
    ListAdapter<Item, RecipesAdapter.ViewHolder>(diffUtilCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Item.RecipeItem -> VIEW_TYPE_RECIPE
            is Item.DateItem -> VIEW_TYPE_DATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            VIEW_TYPE_DATE -> ViewHolder.HeaderViewHolder(
                ItemDateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            VIEW_TYPE_RECIPE -> ViewHolder.RecipeViewHolder(
                ItemRecipeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                listener
            )

            else -> throw IllegalStateException("Unknown view type [$viewType]")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (getItemViewType(position)) {
            VIEW_TYPE_DATE -> bindDate(holder as ViewHolder.HeaderViewHolder, item as Item.DateItem)
            VIEW_TYPE_RECIPE -> (holder as ViewHolder.RecipeViewHolder).bindRecipe(
                holder, item as Item.RecipeItem
            )
        }
    }

    private fun bindRecipe(
        recipeViewHolder: ViewHolder.RecipeViewHolder,
        recipeItem: Item.RecipeItem
    ) {
        with(recipeViewHolder.binding) {
            recipeTitle.text = recipeItem.name
            recipeDescription.text = recipeItem.headline
            Glide
                .with(root.context)
                .load(recipeItem.image)
                .centerCrop()
                .into(recipeImage)
        }
    }

    private fun bindDate(
        headerViewHolder: ViewHolder.HeaderViewHolder,
        dateItem: Item.DateItem
    ) {
        with(headerViewHolder.binding) {
            dateTextView.text = dateItem.date
        }
    }

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class RecipeViewHolder(
            val binding: ItemRecipeBinding,
            listener: (Item.RecipeItem) -> Unit
        ) :
            ViewHolder(binding.root) {

            private var recipeItem: Item.RecipeItem? = null

            init {
                binding.root.setOnClickListener { recipeItem?.let { listener.invoke(it) } }
            }

            fun bindRecipe(
                recipeViewHolder: RecipeViewHolder,
                recipeItem: Item.RecipeItem
            ) {
                this.recipeItem = recipeItem
                with(recipeViewHolder.binding) {
                    recipeTitle.text = recipeItem.name
                    recipeDescription.text = recipeItem.headline
                    Glide
                        .with(root.context)
                        .load(recipeItem.image)
                        .centerCrop()
                        .into(recipeImage)
                }
            }
        }

        class HeaderViewHolder(val binding: ItemDateBinding) :
            ViewHolder(binding.root)
    }

    private companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }

        const val VIEW_TYPE_RECIPE = 0
        const val VIEW_TYPE_DATE = 1
    }
}
