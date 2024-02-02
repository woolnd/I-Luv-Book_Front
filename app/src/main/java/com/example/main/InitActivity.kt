package com.example.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.main.databinding.ActivityInitBinding

class InitActivity : AppCompatActivity() {

    lateinit var binding: ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.navigationBarColor = Color.TRANSPARENT
        setFragment(InitFragment())

    }

    //프래그먼트 띄우기
    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.init_fl, fragment)
        transaction.commit()
    }

}