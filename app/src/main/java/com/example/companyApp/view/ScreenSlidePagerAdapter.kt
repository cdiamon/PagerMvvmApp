package com.example.companyApp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment().apply {
        arguments = Bundle().apply { putInt(KEY_PAGE, position) }
    }

    companion object {
        private const val NUM_PAGES = 3
        const val KEY_PAGE = "KEY_PAGE"

        enum class ScreenType {
            MANUFACTURERS, MAIN_TYPES, BUILD_DATES
        }
    }
}
