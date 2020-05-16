package com.example.pollapp.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.SET_DEFAULT
import androidx.room.PrimaryKey

@Entity(tableName = "question_table",
        foreignKeys = [
            ForeignKey(entity = QuestionType::class,
            parentColumns = ["id"],
            childColumns = ["type_id"],
            onDelete = SET_DEFAULT)
        ])
data class Question(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var questionId: Long = 0L,

    @NonNull
    val text: String,

    var answer: String = "",

    var type_id: Long = 0L
)