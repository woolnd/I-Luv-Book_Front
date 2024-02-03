package com.example.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.main.databinding.ActivityQuizTestBinding

class QuizTestActivity: AppCompatActivity() {

    lateinit var binding: ActivityQuizTestBinding
    private val handler = Handler()

    private var currentQuizIndex = 0 // 현재 보여지는 퀴즈의 인덱스

    private var correct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val quizDataList = listOf(
            QuizData("다음 중 니콜라스의 아침 인사를 받은 대상은 누구인가요?", "엄마", "태양", "도끼", 2),
            QuizData("니콜라스 가족은 어디에서 소풍을 가기로 결정했나요?", "호수", "강가", "산", 2),
            QuizData("니콜라스가 소풍에 데려가고 싶어한 친구의 이름은 무엇인가요?", "제이콥", "도끼", "개", 1),
            QuizData(" 다음 중 소풍에 가고 싶어하는 동물은 누구인가요?", "새", "도키", "개", 2),
            QuizData("니콜라스와 경주에서 이긴 동물은 누구인가요?", "새", "도키", "개", 2)
        )


        showQuiz(quizDataList[currentQuizIndex], currentQuizIndex)

        binding.select1Iv.setOnClickListener {
            binding.select1Iv.setImageResource(R.drawable.quiz_correct)
            binding.selectBtn1Iv.setImageResource(R.drawable.quiz_choice_1)
            binding.nextIv.visibility = View.VISIBLE
            processAnswer(1, quizDataList)
        }

        binding.select2Iv.setOnClickListener {
            binding.select2Iv.setImageResource(R.drawable.quiz_correct)
            binding.selectBtn2Iv.setImageResource(R.drawable.quiz_choice_2)
            binding.nextIv.visibility = View.VISIBLE
            processAnswer(2, quizDataList)
        }

        binding.select3Iv.setOnClickListener {
            binding.select3Iv.setImageResource(R.drawable.quiz_correct)
            binding.selectBtn3Iv.setImageResource(R.drawable.quiz_choice_3)
            binding.nextIv.visibility = View.VISIBLE
            processAnswer(3, quizDataList)
        }
    }
    private fun showQuiz(quizData: QuizData, currentQuizIndex: Int) {
        var current = currentQuizIndex.toInt() + 1
        binding.quizChipTv.text = "Quiz." + current.toString()
        binding.quizTitleTv.text = "A Beautiful Day"
        binding.quizContentTv.text = quizData.title
        binding.select1Tv.text = quizData.option1
        binding.select2Tv.text = quizData.option2
        binding.select3Tv.text = quizData.option3
    }

    private fun processAnswer(selectedAnswer: Int, quizDataList: List<QuizData>) {
        val currentQuiz = quizDataList[currentQuizIndex]

        when(currentQuiz.answer){
            1 ->{
                binding.answer1Iv.setImageResource(R.drawable.quiz_answer)
                binding.answer1Iv.visibility = View.VISIBLE
                binding.answer2Iv.setImageResource(R.drawable.quiz_error)
                binding.answer2Iv.visibility = View.VISIBLE
                binding.answer3Iv.setImageResource(R.drawable.quiz_error)
                binding.answer3Iv.visibility = View.VISIBLE
            }
            2 ->{
                binding.answer1Iv.setImageResource(R.drawable.quiz_error)
                binding.answer1Iv.visibility = View.VISIBLE
                binding.answer2Iv.setImageResource(R.drawable.quiz_answer)
                binding.answer2Iv.visibility = View.VISIBLE
                binding.answer3Iv.setImageResource(R.drawable.quiz_error)
                binding.answer3Iv.visibility = View.VISIBLE
            }
            3->{
                binding.answer1Iv.setImageResource(R.drawable.quiz_error)
                binding.answer1Iv.visibility = View.VISIBLE
                binding.answer2Iv.setImageResource(R.drawable.quiz_error)
                binding.answer2Iv.visibility = View.VISIBLE
                binding.answer3Iv.setImageResource(R.drawable.quiz_answer)
                binding.answer3Iv.visibility = View.VISIBLE
            }
        }

        if (selectedAnswer == currentQuiz.answer) {
            // 정답일 때의 처리
            correct++
            when(currentQuizIndex){
                0 ->{
                    binding.quizStar1Iv.setImageResource(R.drawable.quiz_star_on)
                }
                1 ->{
                    binding.quizStar2Iv.setImageResource(R.drawable.quiz_star_on)
                }
                2 ->{
                    binding.quizStar3Iv.setImageResource(R.drawable.quiz_star_on)
                }
                3 ->{
                    binding.quizStar4Iv.setImageResource(R.drawable.quiz_star_on)
                }
                4 ->{
                    binding.quizStar5Iv.setImageResource(R.drawable.quiz_star_on)
                }
            }
        } else {
            when(currentQuizIndex){
                0 ->{
                    binding.quizStar1Iv.setImageResource(R.drawable.quiz_star_error)
                }
                1 ->{
                    binding.quizStar2Iv.setImageResource(R.drawable.quiz_star_error)
                }
                2 ->{
                    binding.quizStar3Iv.setImageResource(R.drawable.quiz_star_error)
                }
                3 ->{
                    binding.quizStar4Iv.setImageResource(R.drawable.quiz_star_error)
                }
                4 ->{
                    binding.quizStar5Iv.setImageResource(R.drawable.quiz_star_error)
                }
            }
        }

        binding.nextIv.setOnClickListener {
            binding.answer1Iv.visibility = View.INVISIBLE
            binding.answer2Iv.visibility = View.INVISIBLE
            binding.answer3Iv.visibility = View.INVISIBLE

            binding.select1Iv.setImageResource(R.drawable.quiz_select)
            binding.selectBtn1Iv.setImageResource(R.drawable.quiz_select_1)

            binding.select2Iv.setImageResource(R.drawable.quiz_select)
            binding.selectBtn2Iv.setImageResource(R.drawable.quiz_select_2)

            binding.select3Iv.setImageResource(R.drawable.quiz_select)
            binding.selectBtn3Iv.setImageResource(R.drawable.quiz_select_3)

            binding.nextIv.visibility = View.INVISIBLE

            // 다음 퀴즈로 넘어가는 로직
            currentQuizIndex++

            if (currentQuizIndex < quizDataList.size) {
                showQuiz(quizDataList[currentQuizIndex], currentQuizIndex)
            } else {
                // 마지막 퀴즈까지 끝났을 때의 처리
                val intent = Intent(this@QuizTestActivity, QuizFinishActivity::class.java)
                intent.putExtra("correct", correct)
                startActivity(intent)
            }
        }
        binding.testBackIv.setOnClickListener {
            finish()
        }
    }
}