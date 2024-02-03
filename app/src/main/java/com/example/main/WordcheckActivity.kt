package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.main.databinding.ActivityWordcheckBinding

class WordcheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordcheckBinding
    private val wordPairs = listOf(
        WordPair("beautiful", "아름다운"),
        WordPair("morning", "아침"),
        WordPair("lovely", "사랑스러운"),
        WordPair("day", "일"),
        WordPair("picnic", "소풍"),
        WordPair("dog", "개"),
        WordPair("friend", "친구"),
        WordPair("leg", "다리"),
        WordPair("bird", "새"),
        WordPair("tree", "나무")
        // 여기에 더 많은 단어 쌍을 추가할 수 있습니다.
    )
    private val unknownWords = mutableListOf<WordPair>()
    private val knownWords = mutableListOf<WordPair>()

    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordcheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wordChBackIv.setOnClickListener{
            finish()
        }

        updateWord()

        binding.wordChMeaningshowIv.setOnClickListener {
            if (binding.wordChKorTv.visibility == View.VISIBLE) {
                wordInvisible()
            } else {
                wordVisible()
            }
        }

        binding.wordChUnknowIv.setOnClickListener {
            if (currentIndex < wordPairs.size) {
                unknownWords.add(wordPairs[currentIndex])
                currentIndex++
                updateWord()
                if (binding.wordChKorTv.visibility == View.VISIBLE) {
                    wordInvisible()
                }
            }
        }

        binding.wordChKnowIv.setOnClickListener {
            if (currentIndex < wordPairs.size) {
                knownWords.add(wordPairs[currentIndex])
                currentIndex++
                updateWord()
                if (binding.wordChKorTv.visibility == View.VISIBLE) {
                    wordInvisible()
                }
            }
        }
    }

    private fun updateWord() {
        if (currentIndex < wordPairs.size) {
            val wordPair = wordPairs[currentIndex]
            binding.wordChEngTv.text = wordPair.english
            binding.wordChKorTv.text = wordPair.korean
        } else {
            val intent = Intent(this, WordafterActivity::class.java)

            val knownWordsList = ArrayList(knownWords)
            val unknownWordsList = ArrayList(unknownWords)

            val intent1 = Intent(this, WordafterActivity::class.java)
//            intent1.putExtra("knownWords", knownWordsList)
//            intent1.putExtra("unknownWords", unknownWordsList)
            startActivity(intent1)

            currentIndex = 0
            knownWords.clear()
            unknownWords.clear()
        }
    }

    private fun wordInvisible(){
            binding.wordChMeaningshowIv.setImageResource(R.drawable.word_meaningshow)
            binding.wordChKorTv.visibility = View.GONE
            val params = binding.wordChEngTv.layoutParams as ConstraintLayout.LayoutParams
            params.topMargin = 450
            binding.wordChEngTv.layoutParams = params
        }

    private fun wordVisible() {
        binding.wordChMeaningshowIv.setImageResource(R.drawable.word_meaningshow_after)
        binding.wordChKorTv.visibility = View.VISIBLE
        val params = binding.wordChEngTv.layoutParams as ConstraintLayout.LayoutParams
        params.topMargin = 430
        binding.wordChEngTv.layoutParams = params
    }
}