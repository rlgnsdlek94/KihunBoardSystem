package com.example.kbs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.kbs.R
import com.example.kbs.view.CanvasView

class CanvasFragment : Fragment() {
    private lateinit var canvasView: CanvasView
    private lateinit var viewPager: ViewPager2



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.canvas_fragment, container, false)
        canvasView = view.findViewById(R.id.canvas_view)
        viewPager = requireActivity().findViewById(R.id.view_pager)

        // CanvasView 터치 이벤트로 슬라이드 제어
        canvasView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> viewPager.isUserInputEnabled = false
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> viewPager.isUserInputEnabled = true
            }
            false // CanvasView의 터치 이벤트도 처리해야 하므로 false 반환
        }

        val saveButton: Button = view.findViewById(R.id.save_button)
        val resetButton: Button = view.findViewById(R.id.reset_button)

        // 저장 버튼 클릭 리스너
        saveButton.setOnClickListener {
            // CanvasView 내용을 저장하는 로직 추가
            // Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // 다시 그리기 버튼 클릭 리스너
        resetButton.setOnClickListener {
            canvasView.clear() // CanvasView에 clear() 메서드 구현 필요
            //Toast.makeText(this, "다시 그리기 완료", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}