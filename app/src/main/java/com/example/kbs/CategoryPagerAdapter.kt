package com.example.kbs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kbs.fragment.CalculatorFragment
import com.example.kbs.fragment.CalendarFragment
import com.example.kbs.fragment.CanvasFragment
import com.example.kbs.fragment.MemoFragment
import com.example.kbs.fragment.StopWatchFragment

class CategoryPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    var categories: List<String> = emptyList()

    fun setCategorise() {
        // 언어에 따라 카테고리 데이터를 업데이트
        categories = listOf("계산기","스톱워치","달력","메모","그림판") //, "ETC"
        notifyDataSetChanged() // 데이터 변경을 어댑터에 알림
    }

    override fun getItemCount(): Int = categories.count()

    override fun createFragment(position: Int): Fragment {

        // 각 카테고리에 대한 프래그먼트를 반환하는 코드를 여기에 작성
        // 예를 들어, 각 카테고리에 해당하는 프래그먼트를 생성하여 반환하는 코드를 작성
        return when (position) {
            0 -> CalculatorFragment()
            1 -> StopWatchFragment()
            2 -> CalendarFragment()
            3 -> MemoFragment()
            4 -> CanvasFragment()
            else -> CalculatorFragment() //WordFragment()
        }
    }

}
