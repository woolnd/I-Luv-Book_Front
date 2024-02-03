package com.example.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.main.databinding.ActivityAgeSetBinding

class AgeSetActivity: AppCompatActivity() {

    lateinit var binding: ActivityAgeSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgeSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ageBtnlowIv.setOnClickListener {
            val intent = Intent(this@AgeSetActivity, InitActivity::class.java)
            startActivity(intent)
        }

        binding.ageBtnmiddleIv.setOnClickListener {
            val intent = Intent(this@AgeSetActivity, InitActivity::class.java)
            startActivity(intent)
        }

        binding.ageBtnhighIv.setOnClickListener {
            val intent = Intent(this@AgeSetActivity, InitActivity::class.java)
            startActivity(intent)
        }
    }
}