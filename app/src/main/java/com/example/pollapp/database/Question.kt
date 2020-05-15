package com.example.pollapp.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(

    @PrimaryKey(autoGenerate = true)
    var questionId: Long = 0L,

    @NonNull
    val text: String,

    var answer: String = ""
)