package com.example.companyApp.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.companyApp.R

class DataAdapter(
    private val onManufactorerClicked: (Pair<String,String>) -> Unit,
) : ListAdapter<Pair<String, String>, GalleryProductViewHolder>(GalleryProductDifferCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryProductViewHolder {
        return LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_text_detail, parent, false)
            .let { view ->
                GalleryProductViewHolder(view) { position ->
                    onManufactorerClicked.invoke(getItem(position))
                }
            }
    }

    override fun onBindViewHolder(holder: GalleryProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
