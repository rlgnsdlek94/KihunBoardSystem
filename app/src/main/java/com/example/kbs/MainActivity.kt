package com.example.kbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val viewPager2Adapter = CategoryPagerAdapter(this)
        viewPager.adapter = viewPager2Adapter
        viewPager.currentItem = 0
        viewPager.offscreenPageLimit = viewPager2Adapter.itemCount.coerceAtLeast(1)

        viewPager2Adapter.setCategorise()
        attachTabLayoutMediator(tabLayout, viewPager, viewPager2Adapter)

    }

    private fun attachTabLayoutMediator(
        tabLayout: TabLayout,
        viewPager: ViewPager2,
        adapter: CategoryPagerAdapter
    ) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // 어댑터에서 카테고리를 가져와 탭에 설정
            tab.text = adapter.categories[position]
        }.attach()
    }

}