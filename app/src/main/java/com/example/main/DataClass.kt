package com.example.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WordCard(
    val imageRes: Int,
    val title: String,
    val successCount: Int,
    val totalCount: Int,
    val btnimageRes: Int
)

@Parcelize
data class WordPair(
    val english: String,
    val korean: String
) : Parcelable
