package com.example.companyApp.view.list

import androidx.recyclerview.widget.RecyclerView

class ScrollToEndListener(private val reachedEnd: () -> Unit) : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (recyclerView.canScrollVertically(-1) &&
            !recyclerView.canScrollVertically(1) &&
            newState == RecyclerView.SCROLL_STATE_IDLE
        ) {
            reachedEnd.invoke()
        }
    }
}
