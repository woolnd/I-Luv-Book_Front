package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.main.databinding.ActivityWordafterBinding

class WordafterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordafterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordafterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragment = WordafterFragment()
        val bundle = Bundle()
        bundle.putStringArrayList("knownWords",intent.getStringArrayListExtra("knownWords"))
        bundle.putStringArrayList("unknownWords",intent.getStringArrayListExtra("unknownWords"))
        fragment.arguments = bundle
        setFragment(fragment)

        binding.wordAfBackIv.setOnClickListener{
            val intent = Intent(this, WordActivity::class.java)
            startActivity(intent)
        }

        binding.wordAfRefreshIv.setOnClickListener{
            val intent = Intent(this, WordcheckActivity::class.java)
            startActivity(intent)
        }

        val list = intent.getStringArrayListExtra("unknownWords")
        binding.wordAfCreateIv.setOnClickListener {
            val intent = Intent(this, WordlistActivity::class.java)
            intent.putStringArrayListExtra("unknownWords", list)
            startActivity(intent)
        }
    }

    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.word_fl, fragment)
        transaction.commit()
    }

}