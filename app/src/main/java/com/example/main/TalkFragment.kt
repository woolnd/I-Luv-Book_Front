package com.example.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.databinding.FragmentBookBinding
import com.example.main.databinding.FragmentTalkBinding

class TalkFragment: Fragment() {

    lateinit var binding: FragmentTalkBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTalkBinding.inflate(inflater, container, false)


        binding.bookKeywordRv.apply {
            adapter = arguments?.getStringArrayList("keywords")?.let { KeywordBookAdapter().build(it) }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.bookTitleTv.text = arguments?.getString("title")


        binding.bookContentRv.apply {
            adapter = arguments?.getStringArrayList("eng")?.let { TalkAdapter().build(it) }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        return binding.root
    }
}