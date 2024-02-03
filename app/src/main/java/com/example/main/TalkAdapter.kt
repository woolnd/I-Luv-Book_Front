package com.example.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.ItemContentBinding
import com.example.main.databinding.ItemTalkBinding

class TalkAdapter: RecyclerView.Adapter<TalkAdapter.ViewHolder>(){

    lateinit var items: ArrayList<String>

    fun build(i: ArrayList<String>) : TalkAdapter{
        items = i
        return this
    }

    class ViewHolder(val binding: ItemTalkBinding, val context: Context): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String) {
            if(item.isEmpty()){
                binding.plusCl.visibility = View.VISIBLE
                binding.c1.setOnClickListener {
                    binding.plusCl.visibility = View.GONE
                    binding.plus1Cl.visibility = View.VISIBLE
                    binding.plus2Cl.visibility = View.VISIBLE
                    binding.c21.setOnClickListener {
                        binding.plus2Cl.visibility = View.GONE
                        binding.funCl.visibility = View.VISIBLE
                    }
                }
            }else{
                binding.contentTv.text = item
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalkAdapter.ViewHolder =
        TalkAdapter.ViewHolder(
            ItemTalkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )

    override fun onBindViewHolder(holder: TalkAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}