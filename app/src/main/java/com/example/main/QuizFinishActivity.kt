package com.example.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.main.databinding.ActivityQuizFinishBinding

class QuizFinishActivity: AppCompatActivity() {

    lateinit var binding: ActivityQuizFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when(intent.getIntExtra("correct", 0)){
            0->{
                binding.backgroundIv.setImageResource(R.drawable.quiz_0)
            }
            1->{
                binding.backgroundIv.setImageResource(R.drawable.quiz_1)
            }
            2->{
                binding.backgroundIv.setImageResource(R.drawable.quiz_2)
            }
            3->{
                binding.backgroundIv.setImageResource(R.drawable.quiz_3)
            }
            4->{
                binding.backgroundIv.setImageResource(R.drawable.quiz_4)
            }
            5->{
                binding.backgroundIv.setImageResource(R.drawable.quiz_5)
            }

        }
        binding.finishIv.setOnClickListener {
            val intent = Intent(this@QuizFinishActivity, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}