package com.example.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.ItemInitKeywordBinding

class InitKeywordAdapter : RecyclerView.Adapter<InitKeywordAdapter.ViewHolder>(){

    lateinit var items: ArrayList<String>

    fun build(i : ArrayList<String>) : InitKeywordAdapter{
        items = i
        return this
    }

    class ViewHolder(val binding: ItemInitKeywordBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String){
            binding.keywordTv.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InitKeywordAdapter.ViewHolder = ViewHolder(
        ItemInitKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


}