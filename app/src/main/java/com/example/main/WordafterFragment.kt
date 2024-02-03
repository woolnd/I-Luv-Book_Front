package com.example.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.main.databinding.FragmentWordafterBinding
import kotlin.math.roundToInt

class WordafterFragment : Fragment() {

    lateinit var binding: FragmentWordafterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordafterBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).roundToInt()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val knownWords = listOf(
            WordPair("beautiful", "아름다운"),
            WordPair("morning", "아침"),
            WordPair("lovely", "사랑스러운"),
            WordPair("day", "일")
            // 여기에 더 많은 단어 쌍을 추가할 수 있습니다.
        )
        val unknownWord = listOf(
            WordPair("picnic", "소풍"),
            WordPair("dog", "개"),
            WordPair("friend", "친구"),
            WordPair("leg", "다리"),
            WordPair("bird", "새"),
            WordPair("tree", "나무")
        )

        val scale: Float = resources.displayMetrics.scaledDensity
        val parentLayout_wrong = binding.wordAfKeywordWrongLl
        val originaltv_wrong = binding.wordAfKeywordWrongTv
        val parentLayout_correct = binding.wordAfKeywordCorrectLl
        val originaltv_correct = binding.wordAfKeywordCorrectTv

        originaltv_wrong.visibility = View.GONE
        originaltv_correct.visibility = View.GONE

        knownWords?.forEach { wordPair ->
            val dynamictv_wrong = TextView(requireContext())
            dynamictv_wrong.text = "${wordPair.english} : ${wordPair.korean}"
            dynamictv_wrong.textSize = originaltv_wrong.textSize / scale
            dynamictv_wrong.setTextColor(originaltv_wrong.currentTextColor)
            dynamictv_wrong.typeface = originaltv_wrong.typeface
            dynamictv_wrong.gravity = originaltv_wrong.gravity
            dynamictv_wrong.background = originaltv_wrong.background
            dynamictv_wrong.setPadding(
                originaltv_wrong.paddingStart,
                originaltv_wrong.paddingTop,
                originaltv_wrong.paddingEnd,
                originaltv_wrong.paddingBottom
            )
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = dpToPx(6)
            }
            dynamictv_wrong.layoutParams = layoutParams
            parentLayout_wrong.addView(dynamictv_wrong)
        }

        unknownWord?.forEach { wordPair ->
            val dynamictv_correct = TextView(requireContext())
            dynamictv_correct.text = "${wordPair.english} : ${wordPair.korean}"
            dynamictv_correct.textSize = originaltv_correct.textSize / scale
            dynamictv_correct.setTextColor(originaltv_correct.currentTextColor)
            dynamictv_correct.typeface = originaltv_correct.typeface
            dynamictv_correct.gravity = originaltv_correct.gravity
            dynamictv_correct.background = originaltv_correct.background
            dynamictv_correct.setPadding(
                originaltv_correct.paddingStart,
                originaltv_correct.paddingTop,
                originaltv_correct.paddingEnd,
                originaltv_correct.paddingBottom
            )
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = dpToPx(6)
            }
            dynamictv_correct.layoutParams = layoutParams
            parentLayout_correct.addView(dynamictv_correct)
        }
    }
}
