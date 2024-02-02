package com.example.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.databinding.FragmentBookBinding

class BookFragment: Fragment() {

    lateinit var binding: FragmentBookBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(inflater, container, false)


        binding.bookKeywordRv.apply {
//            adapter = KeywordBookAdapter().build(keywords)
            adapter = arguments?.getStringArrayList("keywords")?.let { KeywordBookAdapter().build(it) }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.bookTitleTv.text = arguments?.getString("title")


        binding.bookContentRv.apply {
            adapter = arguments?.getStringArrayList("eng")?.let { ContentAdapter().build(it) }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        return binding.root
    }

    fun onPrimaryButtonOn() {
        binding.bookContentRv.apply {
            adapter = arguments?.getStringArrayList("kor")?.let { ContentAdapter().build(it) }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun onPrimaryButtonOff() {
        binding.bookContentRv.apply {
            adapter = arguments?.getStringArrayList("eng")?.let { ContentAdapter().build(it) }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}