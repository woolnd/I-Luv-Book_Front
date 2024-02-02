package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.databinding.ActivityWordlistBinding

class WordlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = intent.getParcelableArrayListExtra<WordPair>("unknownWords")

        binding.wordLiContentRv.apply {
            adapter = WordPairAdapter().build(list ?: arrayListOf())
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.wordLiBackIv.setOnClickListener{
            finish()
        }

        binding.wordLiOption2Iv.setOnClickListener{
            val intent = Intent(this, WordcheckActivity::class.java)
            startActivity(intent)
        }
    }
}