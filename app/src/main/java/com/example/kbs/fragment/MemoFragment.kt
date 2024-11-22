package com.example.kbs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kbs.R
import com.example.kbs.adapter.MemoAdapter
import com.example.kbs.databinding.MemoFragmentBinding
import com.example.kbs.databinding.StopWatchFragmentBinding
import com.example.kbs.util.MemoSet

class MemoFragment : Fragment() {

    lateinit var binding : MemoFragmentBinding

    private val memos = listOf(
        MemoSet("리싸이클러뷰 부시기 #1", false),
        MemoSet("리싸이클러뷰 부시기 #2", false),
        MemoSet("리싸이클러뷰 부시기 #3", false),
        MemoSet("리싸이클러뷰 부시기 #4", false),
        MemoSet("리싸이클러뷰 부시기 #5", false),
        MemoSet("리싸이클러뷰 부시기 #6", false),
        MemoSet("리싸이클러뷰 부시기 #7", false),
        MemoSet("리싸이클러뷰 부시기 #8", false),
        MemoSet("리싸이클러뷰 부시기 #9", false),
        MemoSet("리싸이클러뷰 부시기 #10", false),
        MemoSet("리싸이클러뷰 부시기 #11", false),
        MemoSet("리싸이클러뷰 부시기 #12", false),
        MemoSet("리싸이클러뷰 부시기 #13", false),
        MemoSet("리싸이클러뷰 부시기 #14", false),
        MemoSet("리싸이클러뷰 부시기 #15", false),
        MemoSet("리싸이클러뷰 부시기 #16", false),
        MemoSet("리싸이클러뷰 부시기 #17", false),
        MemoSet("리싸이클러뷰 부시기 #18", false),
        MemoSet("리싸이클러뷰 부시기 #19", false),
        MemoSet("리싸이클러뷰 부시기 #20", false)
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MemoFragmentBinding.inflate(inflater, container, false)

        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.memoList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.memoList.adapter = MemoAdapter(memos)
    }

}