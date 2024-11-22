package com.example.kbs.fragment

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kbs.databinding.StopWatchFragmentBinding

class StopWatchFragment : Fragment() {

    // 뒤로가기 버튼을 누른 시각을 저장하는 속성
    private var initTime = 0L

    // 멈춘 시각을 저장하는 속성
    private var pauseTime = 0L

    // 뷰 바인딩 객체
    private lateinit var binding: StopWatchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰 바인딩 초기화
        binding = StopWatchFragmentBinding.inflate(inflater, container, false)

        // Start 버튼 클릭 리스너
        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()

            // 버튼 상태 변경
            binding.startButton.isEnabled = false
            binding.resetButton.isEnabled = true
            binding.stopButton.isEnabled = true
        }

        // Stop 버튼 클릭 리스너
        binding.stopButton.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.startButton.isEnabled = true
            binding.resetButton.isEnabled = true
            binding.stopButton.isEnabled = false
        }

        // Reset 버튼 클릭 리스너
        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.startButton.isEnabled = true
            binding.resetButton.isEnabled = false
            binding.stopButton.isEnabled = false
        }

        return binding.root
    }
}
