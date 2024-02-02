package com.example.main

data class Init_Book(
    var title: String,
    var keywords: ArrayList<String>,
    var heart: Boolean,
    var color: Int
)

data class TaleCreate(
    var model: String?,
    var keywords: ArrayList<String>?
)

data class TaleResponse(
    var title: String?,
    var engTale: String?,
    var keywords: ArrayList<String>?
)

data class TransLate(
    var source: String?,
    var target: String?,
    var text: String?
)

data class QuizData(
    val title: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val answer: Int
)

