package com.example.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.ItemWordbannerBinding

// WordPairAdapter 클래스
class WordPairAdapter : RecyclerView.Adapter<WordPairAdapter.ViewHolder>() {

    private var items: List<WordPair> = emptyList() // 리스트로 변경

    fun setData(wordPairs: List<WordPair>) {
        items = wordPairs
        notifyDataSetChanged() // 데이터 변경 시 어댑터에 알려 갱신
    }

    class ViewHolder(val binding: ItemWordbannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WordPair) {
            binding.wordEng.text = item.english
            binding.wordKor.text = item.korean
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWordbannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
