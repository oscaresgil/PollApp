package com.example.pollapp.database

import androidx.room.Embedded

data class QuestionWithType(
    @Embedded
    val question: Question,

    val type: String
)