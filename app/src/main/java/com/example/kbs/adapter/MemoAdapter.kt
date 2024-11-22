package com.example.kbs.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kbs.util.MemoSet
import com.example.kbs.databinding.ItemMemoBinding

class MemoAdapter(private val memos: List<MemoSet>) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {
    companion object {
        private const val TAG = "MemoAdapter_고기"
    }

    // ViewHolder 생성하는 함수, 최소 생성 횟수만큼만 호출됨 (계속 호출 X)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        val binding =  ItemMemoBinding.inflate(
            LayoutInflater.from(parent.context), // layoutInflater 를 넘기기위해 함수 사용, ViewGroup 는 View 를 상속하고 View 는 이미 Context 를 가지고 있음
            parent, // 부모(리싸이클러뷰 = 뷰그룹)
            false   // 리싸이클러뷰가 attach 하도록 해야함 (우리가 하면 안됨)
        )
        return MemoViewHolder(binding).also { holder ->
            binding.completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                memos.getOrNull(holder.adapterPosition)?.completed = isChecked
            }
        }
    }

    // 만들어진 ViewHolder에 데이터를 바인딩하는 함수
    // position = 리스트 상에서 몇번째인지 의미
    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        holder.bind(memos[position])
    }

    override fun getItemCount(): Int = memos.size

    class MemoViewHolder(private val binding: ItemMemoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menuSet: MemoSet) {
            binding.memoTitleText.text = menuSet.title
            binding.completedCheckBox.isChecked = menuSet.completed
        }
    }
}