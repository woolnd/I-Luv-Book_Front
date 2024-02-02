package com.example.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.ItemContentBinding

class ContentAdapter: RecyclerView.Adapter<ContentAdapter.ViewHolder>(){

    lateinit var items: ArrayList<String>

    fun build(i: ArrayList<String>) : ContentAdapter{
        items = i
        return this
    }

    class ViewHolder(val binding: ItemContentBinding, val context: Context): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String) {
            if(item.isEmpty()){
                binding.funCl.visibility = View.VISIBLE
                binding.quizBtnIv.setOnClickListener {
                    val intent = Intent(context, QuizTestActivity::class.java)
                    context.startActivity(intent)
                }
            }else{
                binding.contentTv.text = item
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapter.ViewHolder =
        ContentAdapter.ViewHolder(
            ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}