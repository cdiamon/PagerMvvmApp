package com.example.companyApp.view.list

import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.companyApp.R

class BackgroundItemDecoration(
    @param:ColorRes private val mOddBackground: Int = android.R.color.white,
    @param:DrawableRes private val mEvenBackground: Int = R.color.lt_grey
) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        view.setBackgroundResource(if (position % 2 == 0) mEvenBackground else mOddBackground)
    }
}
