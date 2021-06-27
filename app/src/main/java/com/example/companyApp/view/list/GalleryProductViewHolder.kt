package com.example.companyApp.view.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_text_detail.view.*

class GalleryProductViewHolder(
    override val containerView: View,
    onClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        itemView.setOnClickListener {
            onClickListener.invoke(adapterPosition)
        }
    }

    fun bind(product: Pair<String, String>) {
        containerView.detailTv.text = product.second
    }
}
