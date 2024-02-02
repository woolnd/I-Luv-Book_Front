package com.example.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.ItemWordbannerBinding

class WordPairAdapter : RecyclerView.Adapter<WordPairAdapter.ViewHolder>(){

    lateinit var items: ArrayList<WordPair>

    fun build(i : ArrayList<WordPair>) : WordPairAdapter{
        items = i
        return this
    }

    class ViewHolder(val binding: ItemWordbannerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: WordPair){ //이 부분을 변경했습니다.
            binding.wordEng.text = item.english // 수정한 코드
            binding.wordKor.text = item.korean  // 수정한 코드
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordPairAdapter.ViewHolder = ViewHolder(
        ItemWordbannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
