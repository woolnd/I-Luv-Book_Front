package com.example.main

import WordCardAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.databinding.FragmentWordBinding

class WordFragment: Fragment() {

    lateinit var binding: FragmentWordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordBinding.inflate(inflater, container, false)

        binding.wordStudyingRv.layoutManager = LinearLayoutManager(context)

        val wordCards = listOf(
            WordCard(R.drawable.word_book_card1, "Happy lucy Day!", 8, 10, R.drawable.word_book_startbtn1),
            WordCard(R.drawable.word_book_card2, "The dog and His bone", 5, 12, R.drawable.word_book_startbtn2),
            WordCard(R.drawable.word_book_card3, "I luv Book", 10, 10, R.drawable.word_book_startbtn3),
            WordCard(R.drawable.word_book_card4, "I am happy dog", 4, 8, R.drawable.word_book_startbtn4)
        )
        val adapter = WordCardAdapter(wordCards)
        binding.wordStudyingRv.adapter = adapter

        adapter.setOnItemClickListener(object : WordCardAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context, WordcheckActivity::class.java)
                startActivity(intent)
            }
        })

        return binding.root
    }
}