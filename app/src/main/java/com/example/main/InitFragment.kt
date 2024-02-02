package com.example.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.databinding.FragmentInitBinding

class InitFragment: Fragment() {

    lateinit var binding: FragmentInitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitBinding.inflate(inflater, container, false)


        val books = arrayListOf<Init_Book>(
            Init_Book("The Magic of Knowledge: Lial’s Journey into Fantasia", arrayListOf("teacher"), false, 1),
            Init_Book("The Pendant of Friendship", arrayListOf("animal", "girl"), true, 2),
            Init_Book("Timmy and Leo’s Enchanted Adventure", arrayListOf("play", "friend"), true, 3),
            Init_Book("The Guardian of the Enchanted Forest", arrayListOf("forest", "animal"), true, 4),
            Init_Book("The Enchanted Journey of Jack, Sunny, and Baba", arrayListOf("father", "guidance", "bravery"), true, 5),
            Init_Book("The Majestic Tiger and the Eternal Summer", arrayListOf("summer", "tiger", "animal", "friend", "house"), false, 1),
        )

        binding.bookRv.apply {
            adapter = InitBookAdapter().build(books)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setNestedScrollingEnabled(false)

        }

        binding.makeBtnIv.setOnClickListener {
            val intent = Intent(context, MakeActivity::class.java)
            //intent.putExtra("memberId", memberId)
            startActivity(intent)
        }

        return binding.root
    }
}