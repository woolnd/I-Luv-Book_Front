package com.example.main

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.main.databinding.ActivityQuizBinding

class QuizActivity: AppCompatActivity() {

    lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#F2F3F5")
        window.navigationBarColor = Color.TRANSPARENT

        setFragment(QuizFragment())
    }


    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.quiz_fl, fragment)
        transaction.commit()
    }
}