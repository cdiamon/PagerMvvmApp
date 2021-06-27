package com.example.companyApp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.companyApp.R
import com.example.companyApp.view.util.DepthPageTransformer
import com.example.companyApp.view.util.NextPageListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NextPageListener {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.isUserInputEnabled = false
        viewPager.adapter = pagerAdapter
        viewPager.setPageTransformer(DepthPageTransformer())
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    override fun onNextPage() {
        if (viewPager.currentItem < 2)
            viewPager.currentItem = viewPager.currentItem + 1
    }
}
