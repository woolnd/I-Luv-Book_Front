package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.databinding.ActivityWordlistBinding

// WordlistActivity 클래스
class WordlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordlistBinding
    private val wordPairAdapter = WordPairAdapter() // 어댑터 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordPairs = listOf(
            WordPair("picnic", "소풍"),
            WordPair("dog", "개"),
            WordPair("friend", "친구"),
            WordPair("leg", "다리"),
            WordPair("bird", "새"),
            WordPair("tree", "나무")
        )

        binding.wordLiContentRv.apply {
            adapter = wordPairAdapter // 생성한 어댑터 설정
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        wordPairAdapter.setData(wordPairs) // 어댑터에 데이터 설정

        binding.wordLiBackIv.setOnClickListener {
            finish()
        }

        binding.wordLiOption2Iv.setOnClickListener {
            val intent = Intent(this, WordcheckActivity::class.java)
            startActivity(intent)
        }
    }
}
